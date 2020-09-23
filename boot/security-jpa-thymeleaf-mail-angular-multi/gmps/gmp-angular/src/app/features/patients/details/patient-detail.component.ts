import {AfterViewInit, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {I18nService} from '@web-core-app/services/i18n/i18n.service';
import {BootstrapTreeviewComponent, TreeNode, TreeEventNode, TreeEventNodeList} from '@web-core-app/features/tree/bootstrap-treeview.component';
import {UserService} from '@web-core-app/services/user.service';
import {AlertService} from '@web-core-app/services/ui/alert.service';
import {ValidationService} from '@web-core-app/services/validation/validation.service';
import {JsonApiService} from '@web-core-app/services/json-api.service';
import {ValidUtil} from '@web-core-app/utils/ValidUtil';
import {CodeService} from '@web-core-app/services/code/code.service';
import {PhenoTypeTreeForBootstrapTreeView, PtntInfo, PtntPntype} from '@web-core-generate/models';
import {of, from, Subscription, forkJoin} from 'rxjs/index';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Title} from '@angular/platform-browser';
import {Observable} from 'rxjs/internal/Observable';
import {HttpHeaders} from '@angular/common/http';

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-patient-regist',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.css'],
    providers: []
})
export class PatientDetailComponent implements OnInit, OnDestroy, AfterViewInit {

    constructor(private route: ActivatedRoute, private valid: ValidationService, private user: UserService,
                private codeService: CodeService,
                private router: Router,
                private i18n: I18nService,
                private api: JsonApiService,
                private alertService: AlertService,
                private title: Title) {
    }

    public paramsSub: Subscription;
    public demo1: any;
    public ptntInfo: PtntInfo = {} as PtntInfo;
    // public hpoTree = new Array<PhenoTypeTree>();
    // public hpoTable = new Array<any>();
    // public info: PatientInfo =  new PatientInfo();
    public phenoTypeTreeWhere = {search: ''};
    infoForm: FormGroup;
    @ViewChild('mri') mri: ElementRef;
    @ViewChild('vcf') vcf: ElementRef;
    @ViewChild(BootstrapTreeviewComponent) treeView: BootstrapTreeviewComponent;
    public checkedPhenoTypes: Map<number, TreeNode<PhenoTypeTreeForBootstrapTreeView> & PhenoTypeTreeForBootstrapTreeView> = new Map();
    public titleI18n = 'Unknown';
    private treeViewData: PhenoTypeTreeForBootstrapTreeView[];


    mriSelectedFile: string;
    // https://stackoverflow.com/questions/46059226/upload-image-with-httpclient
    vcfSelectedFile: string;

    ngOnInit() {
        this.resetDatas();
        const ptntSeq = this.route.snapshot.params.ptntSeq;
        const rxForkJoins: Observable<PhenoTypeTreeForBootstrapTreeView[]| PtntInfo>[] = [
            this.api.get<PhenoTypeTreeForBootstrapTreeView[]>('/metas/phenotypes', {params: {type: 'bootstrap-treeview'}})
        ];
        if (isNaN(ptntSeq)) {
            this.titleI18n = 'Register';
            rxForkJoins.push(of({} as PtntInfo));
        } else {
            this.titleI18n = 'Update';
            rxForkJoins.push(this.api.get<PtntInfo>(`/patients/${ptntSeq}`));
        }
        forkJoin(rxForkJoins).subscribe((it: [PhenoTypeTreeForBootstrapTreeView[], PtntInfo]) => {
                this.treeViewData = it[0];
                this.ptntInfo = it[1];
        }, this.api.errorHandler.bind(this.api));


        this.infoForm = new FormGroup({
            ptntNm: new FormControl('', [
                Validators.required
            ]),
            gen: new FormControl('', [
                Validators.required
            ]),
            age: new FormControl('', [
                Validators.required
            ]),
            search: new FormControl('', [
            ])
        });
    }

    ngAfterViewInit() {
    }
    ngOnDestroy() {
    }
    save() {
        if (this.infoForm.valid) {
            const headers = new HttpHeaders();
            headers.append('Content-Type', 'multipart/form-data');
            const formData = new FormData();
            for (const k of Object.getOwnPropertyNames(this.ptntInfo)) {
                if (!ValidUtil.isNullOrUndefined(this.ptntInfo[k])) {
                    formData.append(k, this.ptntInfo[k]);
                }
            }
            if (this.mri.nativeElement.files.length > 0) {
                formData.append('mri', this.mri.nativeElement.files[0]);
            }
            if (this.vcf.nativeElement.files.length > 0) {
                formData.append('vcf', this.vcf.nativeElement.files[0]);
            }

            const cnt = 0;
            const checkedPhenoTypes = Array.from(this.checkedPhenoTypes.values()).map(it => it.id);
            const checkedPhenoTypesString = checkedPhenoTypes.sort().join(',');
            const ptntInfoPtntPntypes = this.ptntInfo?.ptntPntypes?.map(it => it.termId);
            // 변경이 되었으면 또는 없을시.
            if (!ptntInfoPtntPntypes || ptntInfoPtntPntypes.sort().join(',')  !== checkedPhenoTypesString) {
                formData.set('hpoProcStatCd', 'FST002');
            }
            formData.set('ptntPntypes', checkedPhenoTypesString);
            // 수정
            if (this.ptntInfo.ptntSeq) {
                this.api.put(`/patients/${this.ptntInfo.ptntSeq}`, {
                    params: formData, headers
                }).subscribe(it => {
                    this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                    this.router.navigate(['/patients']);
                }, this.api.errorHandler.bind(this.api));
            } else {
                this.api.post(`/patients`, {
                    params: formData, headers
                }).subscribe(it => {
                    this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                    this.router.navigate(['/patients']);
                }, this.api.errorHandler.bind(this.api));
            }
        }else {
            this.valid.validCheck(this.infoForm);
        }
    }


    resetDatas() {
        this.checkedPhenoTypes.clear();
        this.treeViewData = [];
        this.ptntInfo = {} as PtntInfo;
    }

    search() {
    }

    checked($event: TreeEventNode<PhenoTypeTreeForBootstrapTreeView>) {
        this.checkedPhenoTypes.set($event.node.id, $event.node);
    }

    unChecked($event: TreeEventNode<PhenoTypeTreeForBootstrapTreeView>) {
        console.log('unChecked', $event);
        this.checkedPhenoTypes.delete($event.node.id);
    }

    treeSearch() {
        if (this.phenoTypeTreeWhere.search) {
            const searchs = this.treeView.search(this.phenoTypeTreeWhere.search);
            console.log('search ', searchs);
        } else {
            this.treeView.clearSearch();
            this.treeView.collapseAll();
        }
        return false;
    }

    treeCollapseAll() {
        this.phenoTypeTreeWhere.search = '';
        this.treeView.clearSearch();
        this.treeView.collapseAll();
    }

    renderComplet($event: TreeEventNodeList<PhenoTypeTreeForBootstrapTreeView>) {
        // console.log('rendercomplet', $event);
        // const temp: TreeNode<any>[] = [];
        // const i = {nodeId: 0} as TreeNode<any>;
        // temp.push(i);
        // this.treeView.checkNode(temp);
        // console.log('event', $event);
        if (this.ptntInfo.ptntPntypes && this.ptntInfo.ptntPntypes.length > 0 && $event.nodes && $event.nodes.length > 0){
            const termIds = this.ptntInfo.ptntPntypes.map(it => it.termId);
            const nodeTermIds = $event.nodes.filter(it => termIds.includes(it.id));
            // console.log('termIds', termIds, nodeTermIds);
            this.treeView.checkNode(nodeTermIds);
            // this.treeView.revealNode(nodeTermIds);
        }
    }

    treeSelected(idKey: number) {
        const chose = this.checkedPhenoTypes.get(idKey);
        this.treeView.collapseAll();
        this.treeView.revealNode([chose]);
        this.treeView.selectNode([chose]);
    }
    deletePhenoType(idKey: number) {
        const chose = this.checkedPhenoTypes.get(idKey);
        this.treeView.uncheckNode([chose]);
        this.checkedPhenoTypes.delete(idKey);
    }


    public getStatClass(cd: string) {
        let str = 'badge-light'; // 	미등록
        if (cd === 'FST002') {// 	파일 등록
            str = 'badge-primary';
        } else if (cd === 'FST003') { // 	처리 중
            str = 'badge-warning';
        } else if (cd === 'FST004') { // 	완료
            str = 'badge-success';
        } else if (cd === 'FST005') { // 	실패
            str = 'badge-danger';
        }
        return str;
    }

    vcfChange() {
        if (this.vcf.nativeElement.files && this.vcf.nativeElement.files.length > 0) {
            this.vcfSelectedFile = this.vcf.nativeElement.files[0].name;
        } else {
            this.vcfSelectedFile = '';
        }
    }

    mriChange() {
        if (this.mri.nativeElement.files && this.mri.nativeElement.files.length > 0) {
            this.mriSelectedFile = this.mri.nativeElement.files[0].name;
        } else {
            this.mriSelectedFile = '';
        }
    }

    delete() {
        this.api.delete(`/patients/${this.ptntInfo.ptntSeq}`).subscribe(it => {
            this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
            this.router.navigate(['/patients']);
        }, this.api.errorHandler.bind(this.api));
    }
}
