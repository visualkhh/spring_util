import {Component, OnInit, Output, Renderer2, ViewChild, EventEmitter} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import {Adm, Brd, Auth, CorpGrp} from '@web-core-generate/models';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {Router} from '@angular/router';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {ValidUtil} from '@web-core/app/utils/ValidUtil';
import {HttpHeaders} from '@angular/common/http';

declare let $: any;
declare let moment: any;

export interface Where {
    top: number;
    phenoType: boolean;
    vcf: boolean;
    // mri: boolean;
}

@Component({
    selector: 'web-core-adm-modal',
    styleUrls: ['adm-modal.component.css'],
    templateUrl: './adm-modal.component.html',
})
export class AdmModalComponent implements OnInit {

    @Output() complete = new EventEmitter();
    @ViewChild('modalTemplate') modalTemplate;
    // infoForm: FormGroup;
    bsModalRef: BsModalRef;
    config = {
        class: 'modal-md', // sm, xl
        ignoreBackdropClick: false
    };
    infoForm: FormGroup;

    // admInfo: Adm;
    private corps: CorpGrp[];
    private auths: Auth[];

    constructor(private modalService: BsModalService, private validationService: ValidationService, private formBuilder: FormBuilder,
                private cookieService: CookieService, private alertService: AlertService, private api: JsonApiService,
                private i18n: I18nService, private momentService: MomentService, public codeService: CodeService, private renderer: Renderer2, private router: Router) {
    }

    ngOnInit() {
        this.resetWhere();
    }

    setFormGroup(adm?: Adm) {
        this.infoForm = new FormGroup({
            admSeq: new FormControl(adm?.admSeq || '', []),
            email: new FormControl(adm?.email || '', [Validators.email]),
            admLginId: new FormControl(adm?.admLginId || '', [Validators.required]),
            admLginPw: new FormControl('', []),
            admLginPwc: new FormControl('', []),
            admNm: new FormControl(adm?.admNm || '', [Validators.required]),
            companyNm: new FormControl(adm?.companyNm || '', []),
            phone: new FormControl(adm?.phone || '', []),
            jobCd: new FormControl(adm?.jobCd || '', []),
            updDt: new FormControl(adm?.updDt || '', []),
            regDt: new FormControl(adm?.regDt || '', []),
            startDt: new FormControl((adm?.startDt ? this.momentService.moment(adm?.startDt).format('YYYY.MM.DD') : '') || '', [Validators.required]),
            endDt: new FormControl((adm?.endDt ? this.momentService.moment(adm?.endDt).format('YYYY.MM.DD') : '') || '', [Validators.required]),
            homeUrl: new FormControl(adm?.homeUrl || '/#/home', []),
            corpGrpSeq: new FormControl(adm?.corpGrpSeq || '', []),
            admAuth: new FormControl(adm?.admAuths[0]?.authId || '', [Validators.required]),
            useCd: new FormControl(adm?.useCd || '', [Validators.required])
        });
    }

    completClose() {
        this.complete.emit();
        this.close();
    }
    close() {
        this.bsModalRef.hide();
    }

    create() {
        if (this.infoForm.valid) {
            const formData = this.infoForm.value;
            if (formData.admLginPw && formData.admLginPw !== formData.admLginPwc) {
                this.alertService.dangerAlert(this.i18n.getTranslation('Password'), this.i18n.getTranslation('ValidError'));
                this.renderer.selectRootElement('#admLginPw').focus();
                return;
            } else {
                const params = Object.assign({} as Adm, formData) as Adm | any;
                if (params.startDt) {
                    params.startDt = this.momentService.moment(params.startDt).toISOString();
                }
                if (params.endDt) {
                    params.endDt = this.momentService.moment(params.endDt).toISOString();
                }
                if (params?.admAuth) {
                    params.admAuths = [{authId: params.admAuth}];
                }
                for (const k of Object.getOwnPropertyNames(params)) {
                    if (ValidUtil.isEmpty(params[k])) {
                        delete params[k];
                    }
                }
                this.api.post<any>(`/setups/adms`, {params})
                    .subscribe((_) => {
                        this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                        this.completClose();
                    }, this.api.errorHandler.bind(this.api));
            }
        } else {
            this.validationService.validCheck(this.infoForm);
        }
    }

    update() {
        if (this.infoForm.valid) {
            const formData = this.infoForm.value;
            if (formData.admLginPw && formData.admLginPw !== formData.admLginPwc) {
                this.alertService.dangerAlert(this.i18n.getTranslation('Password'), this.i18n.getTranslation('ValidError'));
                this.renderer.selectRootElement('#admLginPw').focus();
                return;
            } else {
                const params = Object.assign({} as Adm, formData) as Adm | any;
                if (params.startDt) {
                    params.startDt = this.momentService.moment(params.startDt).toISOString();
                }
                if (params.endDt) {
                    params.endDt = this.momentService.moment(params.endDt).toISOString();
                }
                if (params?.admAuth) {
                    params.admAuths = [{authId: params.admAuth}];
                }
                for (const k of Object.getOwnPropertyNames(params)) {
                    if (ValidUtil.isEmpty(params[k])) {
                        delete params[k];
                    }
                }
                this.api.put<any>(`/setups/adms/${formData.admSeq}`, {params})
                    .subscribe((_) => {
                        this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                        this.completClose();
                    }, this.api.errorHandler.bind(this.api));
            }
        } else {
            this.validationService.validCheck(this.infoForm);
        }
    }

    no() {
        this.close();
    }

    show(admSeq?: number) {
        // this.ptntInfo = undefined;
        // this.evidences = undefined;
        this.resetWhere();
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append('Accept', 'application/json, application/list+json');
        this.api.get<CorpGrp[]>('/setups/corps', {headers}).subscribe(it => {
            this.corps = it;
        }, this.api.errorHandler.bind(this.api));
        this.api.get<Auth[]>('/setups/auths', {headers}).subscribe(it => {
            this.auths = it;
        }, this.api.errorHandler.bind(this.api));

        if (!ValidUtil.isNullOrUndefined(admSeq)) {
            this.api.get<Adm>(`/setups/adms/${admSeq}`)
                .subscribe(data => this.setFormGroup(data), this.api.errorHandler.bind(this.api));
        }
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    }

    resetWhere() {
        this.corps = [];
        this.auths = [];
        this.setFormGroup();
        // setTimeout(() => {
        // }, 100);
    }

    getReasonDetail(caseId: number) {
    }

    changeEndDt(data: Date | any | Event) {
        if (data instanceof Event) {
            const target = data.target as any;
            if (this.validationService.isDateFormat(target.value)) {
                data = this.momentService.moment(target.value, 'YYYY.MM.DD').toDate();
            } else {
                target.value = '';
                this.infoForm.get('endDt').setValue('');
                data = null;
            }
        } else {
            this.infoForm.get('endDt').setValue(this.momentService.moment(data).format('YYYY.MM.DD'));
        }
        this.infoForm.get('endDt').setValue(this.momentService.moment(data).format('YYYY.MM.DD'));
        // this.admInfo.endDt = data;
    }

    changeStartDt(data: Date | any | Event) {
        if (data instanceof Event) {
            const target = data.target as any;
            if (this.validationService.isDateFormat(target.value)) {
                data = this.momentService.moment(target.value, 'YYYY.MM.DD').toDate();
            } else {
                target.value = '';
                this.infoForm.get('startDt').setValue('');
                data = null;
            }
        } else {
            this.infoForm.get('startDt').setValue(this.momentService.moment(data).format('YYYY.MM.DD'));
        }
        this.infoForm.get('startDt').setValue(this.momentService.moment(data).format('YYYY.MM.DD'));
        // this.admInfo.endDt = data;
    }


}
