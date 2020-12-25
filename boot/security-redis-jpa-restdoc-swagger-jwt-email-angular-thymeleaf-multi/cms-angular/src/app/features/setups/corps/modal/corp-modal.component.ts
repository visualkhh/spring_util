import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CoreAuth, AuthDetail, CoreCorpGrp, CorpGrpAuth, CrudTypeCd} from '@generate/models';
import {JsonApiService, UserService} from '@app/services';
import {AlertService} from '@app/services/ui/alert.service';
import {I18nService} from '@app/services/i18n/i18n.service';
import {ValidationService} from '@app/services/validation/validation.service';
import {zip} from 'rxjs/internal/observable/zip';
import {ValidUtil} from '@app/utils/ValidUtil';
import {MomentService} from '@app/services/date/moment.service';

declare let $: any;

@Component({
    selector: 'web-core-corp-modal',
    templateUrl: './corp-modal.component.html',
    styleUrls: ['./corp-modal.component.css'],
})

export class CorpModalComponent implements OnInit {

    @ViewChild('modalTemplate') modalTemplate;

    @Output() complete = new EventEmitter();

    bsModalRef: BsModalRef;
    formGroup: FormGroup;
    private admAuths: AuthDetail[];
    auths: CoreAuth[];

    constructor(private http: HttpClient, private router: Router, private modalService: BsModalService,
                private api: JsonApiService, private userService: UserService, private momentService: MomentService,
                private alertService: AlertService, private i18n: I18nService,
                private formBuilder: FormBuilder, private validationService: ValidationService) {
    }

    ngOnInit() {
        this.setFormGroup();
    }

    // formArray: https://appdividend.com/2019/12/19/angular-formarray-example-formarray-in-angular/
    private setFormGroup(corpGrp?: CoreCorpGrp) {
        this.formGroup = new FormGroup({
            corpGrpSeq: new FormControl(corpGrp?.corpGrpSeq || '', []),
            corpGrpNm: new FormControl(corpGrp?.corpGrpNm || '', [Validators.required]),
            corpGrpAuth: new FormControl(corpGrp?.corpGrpAuths?.map(it => it.authId)[0] || '', [Validators.required]),
            // corpGrpAuths: new FormArray(corpGrp?.corpGrpAuths.map(it => new FormControl(it.authId)) || [], [Validators.required]),
            regDt: new FormControl((corpGrp?.regDt ? this.momentService.moment(corpGrp.regDt).format('YYYY.MM.DD') : '') || '', []),
        });
    }

    public show(corpGrp?: CoreCorpGrp) {
        this.setFormGroup(corpGrp);
        // this.bsModalRef = this.modalService.show(this.modalTemplate);
        let authConfirmUrl = `/setups/corps`;
        if (!ValidUtil.isEmpty(corpGrp?.corpGrpSeq)) {
            authConfirmUrl += `/${corpGrp.corpGrpSeq}`;
        }
        const auths = this.api.get<CoreAuth[]>('/setups/auths');
        zip(auths, this.userService.getAuths(authConfirmUrl)).subscribe((it) => {
            this.auths = it[0];
            this.admAuths = it[1];
            this.bsModalRef = this.modalService.show(this.modalTemplate);
        });
    }

    private closeAndComplet() {
        this.complete.emit();
        this.close();
    }

    close() {
        this.bsModalRef.hide();
    }

    isUpdate() {
        const sw = this.admAuths.filter(it => it.crudTypeCd === CrudTypeCd.PUT).length > 0;
        return true === (!ValidUtil.isEmpty(this.formGroup.get('corpGrpSeq').value)) && sw;
    }

    isDelete() {
        const sw = this.admAuths.filter(it => it.crudTypeCd === CrudTypeCd.DELETE).length > 0;
        return true === (!ValidUtil.isEmpty(this.formGroup.get('corpGrpSeq').value)) && sw;
    }

    isRegister() {
        const sw = this.admAuths.filter(it => it.crudTypeCd === CrudTypeCd.POST).length > 0;
        return true === ValidUtil.isEmpty(this.formGroup.get('corpGrpSeq').value) && sw;
    }

    formGroupToCorpGrp(): CoreCorpGrp {
        const requestBody = Object.assign({}, this.formGroup.value) as CoreCorpGrp | any;
        delete requestBody.regDt;
        requestBody.corpGrpAuths = [];
        if (this.formGroup.get('corpGrpAuth')?.value) {
            requestBody.corpGrpAuths.push({authId: this.formGroup.get('corpGrpAuth').value} as CorpGrpAuth);
        }
        return requestBody;
    }

    update() {
        if (this.formGroup.valid) {
            const params = this.formGroupToCorpGrp();
            this.api.put<any>(`/setups/corps/${params.corpGrpSeq}`, {params}).subscribe((_) => {
                this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                this.closeAndComplet();
            }, this.api.errorHandler.bind(this.api));
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    register() {
        if (this.formGroup.valid) {
            const params = this.formGroupToCorpGrp();
            this.api.post<any>(`/setups/corps`, {params}).subscribe((_) => {
                this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                this.closeAndComplet();
            }, this.api.errorHandler.bind(this.api));
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    delete() {
        const formData = this.formGroup.value as CoreCorpGrp | any;
        this.api.delete(`/setups/corps/${formData.corpGrpSeq}`).subscribe(it => {
            this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
            this.closeAndComplet();
        }, this.api.errorHandler.bind(this.api));
    }


    get names(): FormArray {
        return this.formGroup.get('corpGrpAuths') as FormArray;
    }
}
