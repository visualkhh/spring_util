import {Component, EventEmitter, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import {CoreAdm} from '@generate/models';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '@app/services/ui/alert.service';
import {ValidationService} from '@app/services/validation/validation.service';
import {I18nService} from '@app/services/i18n/i18n.service';
import {CodeService} from '@app/services/code/code.service';
import {JsonApiService} from '@app/services/json-api.service';
import {Router} from '@angular/router';
import {MomentService} from '@app/services/date/moment.service';
import {ValidUtil} from '@app/utils/ValidUtil';

declare let $: any;
declare let moment: any;

export interface Where {
    top: number;
    phenoType: boolean;
    vcf: boolean;
    // mri: boolean;
}

@Component({
    selector: 'web-core-sign-up-modal',
    styleUrls: ['sign-up-modal.component.css'],
    templateUrl: './sign-up-modal.component.html',
})
export class SignUpModalComponent implements OnInit {

    @Output() completed = new EventEmitter();
    @ViewChild('modalTemplate') modalTemplate;
    // infoForm: FormGroup;
    bsModalRef: BsModalRef;
    config = {
        class: 'modal-md', // sm, xl
        ignoreBackdropClick: false
    };
    infoForm: FormGroup;


    constructor(private modalService: BsModalService, private validationService: ValidationService, private formBuilder: FormBuilder,
                private cookieService: CookieService, private alertService: AlertService, private api: JsonApiService,
                private i18n: I18nService, private momentService: MomentService, public codeService: CodeService, private renderer: Renderer2, private router: Router) {
    }

    ngOnInit() {
        this.resetWhere();
    }

    setFormGroup(adm?: CoreAdm) {
        this.infoForm = new FormGroup({
            email: new FormControl(adm?.email || '', [Validators.required, Validators.email]),
            admLginId: new FormControl(adm?.admLginId || '', [Validators.required]),
            admLginPw: new FormControl('', [Validators.required]),
            admLginPwc: new FormControl('', []),
            admNm: new FormControl(adm?.admNm || '', [Validators.required]),
            companyNm: new FormControl(adm?.companyNm || '', []),
            phone: new FormControl(adm?.phone || '', []),
            jobCd: new FormControl(adm?.jobCd || '', []),
        });
    }

    completClose() {
        this.completed.emit();
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
                const params = Object.assign({} as CoreAdm, formData) as CoreAdm | any;
                for (const k of Object.getOwnPropertyNames(params)) {
                    if (ValidUtil.isEmpty(params[k])) {
                        delete params[k];
                    }
                }
                this.api.post<any>(`/anons/adms`, {params})
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

    show() {
        this.resetWhere();
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    }

    resetWhere() {
        this.setFormGroup();
    }


}
