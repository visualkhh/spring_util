import {Component, EventEmitter, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import {Adm} from '@web-core-generate/models';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {Router} from '@angular/router';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {ValidUtil} from '@web-core/app/utils/ValidUtil';

declare let $: any;
declare let moment: any;

export interface Where {
    top: number;
    phenoType: boolean;
    vcf: boolean;
    // mri: boolean;
}

@Component({
    selector: 'web-core-idpwd-find-modal',
    styleUrls: ['idpwd-find-modal.component.css'],
    templateUrl: './idpwd-find-modal.component.html',
})
export class IdpwdFindModalComponent implements OnInit {

    @Output() completed = new EventEmitter();
    @ViewChild('modalTemplate') modalTemplate;
    // infoForm: FormGroup;
    bsModalRef: BsModalRef;
    config = {
        class: 'modal-md', // sm, xl
        ignoreBackdropClick: false
    };
    infoForm: FormGroup;
    public type = 'id';


    constructor(private modalService: BsModalService, private validationService: ValidationService, private formBuilder: FormBuilder,
                private cookieService: CookieService, private alertService: AlertService, private api: JsonApiService,
                private i18n: I18nService, private momentService: MomentService, public codeService: CodeService, private renderer: Renderer2, private router: Router) {
    }

    ngOnInit() {
        this.resetWhere();
    }

    setFormGroup() {
        if (this.isPWDMode()) {
            this.infoForm = new FormGroup({
                email: new FormControl('', [Validators.required, Validators.email]),
                admNm: new FormControl('', [Validators.required]),
                admLginId: new FormControl('', [Validators.required]),
            });
        } else if (this.isIDMode()) {
            this.infoForm = new FormGroup({
                email: new FormControl('', [Validators.required, Validators.email]),
                admNm: new FormControl('', [Validators.required]),
            });
        }
    }

    completClose() {
        this.completed.emit();
        this.close();
    }

    close() {
        this.bsModalRef.hide();
    }

    find() {
        if (this.infoForm.valid) {
            const formData = this.infoForm.value;
            this.api.get<any>(`/anons/finds/${this.type}`, {params: formData})
                .subscribe((_) => {
                    this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                    this.completClose();
                }, this.api.errorHandler.bind(this.api));
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


    changeIDMode() {
        this.type = 'id';
        this.setFormGroup();
    }

    changePWDMode() {
        this.type = 'password';
        this.setFormGroup();
    }

    isIDMode() {
        return this.type === 'id';
    }

    isPWDMode() {
        return this.type === 'password';
    }
}
