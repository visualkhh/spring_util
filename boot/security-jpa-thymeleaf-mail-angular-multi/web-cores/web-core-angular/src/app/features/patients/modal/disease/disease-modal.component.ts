import {Component, OnInit, Renderer2, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import {PtntInfo} from '@web-core-generate/models';
import {FormBuilder} from '@angular/forms';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {CustomEvidenceScore} from '@web-core-generate/models';
import {Router} from '@angular/router';
declare let $: any;
declare let moment: any;

export interface Where {
    top: number;
    phenoType: boolean;
    vcf: boolean;
    // mri: boolean;
}

@Component({
    selector: 'web-core-disease-modal',
    styleUrls: ['disease-modal.component.css'],
    templateUrl: './disease-modal.component.html',
})
export class DiseaseModalComponent implements OnInit {

    // @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild('modalTemplate') modalTemplate;
    // infoForm: FormGroup;
    bsModalRef: BsModalRef;
    config = {
        class: 'modal-xl',
        ignoreBackdropClick: false
    };
    where: Where;

    ptntInfo: PtntInfo;
    evidences: CustomEvidenceScore[];

    constructor(private modalService: BsModalService, private validationService: ValidationService, private formBuilder: FormBuilder,
                private cookieService: CookieService, private alertService: AlertService, private api: JsonApiService,
                private i18n: I18nService, public codeService: CodeService, private renderer: Renderer2, private router: Router) {
    }

    ngOnInit() {
        this.resetWhere();
    }

    close() {
        this.bsModalRef.hide();
    }

    update() {
    }

    no() {
        this.close();
    }

    show(ptntSeq: number) {
        this.ptntInfo = undefined;
        this.evidences = undefined;
        this.resetWhere();
        this.api.get<PtntInfo>(`/patients/${ptntSeq}`)
            .subscribe(data => this.ptntInfo = data, this.api.errorHandler.bind(this.api));
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    }

    resetWhere() {
        this.where = {
            top: 1,
            // mri: false,
            phenoType: false,
            vcf: false
        } as Where;
    }

    search() {
        console.log('where search', this.where);
        if (this.where.top > 0 && (this.where.phenoType || this.where.vcf)) {
            this.api.get<CustomEvidenceScore[]>(`/patients/${this.ptntInfo.ptntSeq}/evidences`, {params: this.where})
                .subscribe(it => this.evidences = it, (e) => this.api.errorHandler(e));
        } else {
            this.alertService.dangerAlert(this.i18n.getTranslation('SearchContition'), this.i18n.getTranslation('ValidError'));
        }
    }

    onCheckboxChange(e: Event) {
        // const checkArray: FormArray = this.infoForm.get('types') as FormArray;
        // if (e.target['checked']) {
        //     checkArray.push(new FormControl(e.target['value']));
        // } else {
        //     let i: number = 0;
        //     checkArray.controls.forEach((item: FormControl) => {
        //         if (item.value == e.target.value) {
        //             checkArray.removeAt(i);
        //             return;
        //         }
        //         i++;
        //     });
        // }
    }

    getReasonDetail(caseId: number) {
    }

    newWindowPatientResult(it: CustomEvidenceScore) {
        this.router.navigate([`/patients/${this.ptntInfo.ptntSeq}/results/${it.customId}`], {queryParams: {viewtype: 'strip'}});
        // this.router.navigateByUrl(`/patients/${this.ptntInfo.ptntSeq}/results/${it.customId}`, {queryParams: {viewtype: 'strip'}}).then(it => {
        //     console.log('---', it);
        // });
        // .then(result => { window.open(result, '_blank'); });
    }
}
