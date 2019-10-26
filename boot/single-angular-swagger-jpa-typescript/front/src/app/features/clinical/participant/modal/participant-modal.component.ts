import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ApiAction, JsonApiService, UserService} from "@app/core/services";
import {NotificationService} from "@app/core/services/notification.service";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {ValidationService} from "@app/core/services/validation.service";
import {Brd, BrdCateCd, GenderCode, ParentCd, PtcpCd, PtcpGrpCd, User, UserDetails} from "@app/model/commomModels";
declare let $: any;
import * as moment from 'moment'

@Component({
    selector: 'app-participant-modal',
    templateUrl: './participant-modal.component.html',
    styleUrls: ['./participant-modal.component.css'],
})

export class ParticipantModalComponent implements OnInit {

    @ViewChild('brdModalTemplate') brdTemplate;
    bsModalRef: BsModalRef;
    public detail = {} as User;
    formGroup: FormGroup;
    @Output() complete = new EventEmitter();
    public i18n: I18nService;

    constructor(private router: Router, private modalService: BsModalService, private jsonApiService: JsonApiService, public userService: UserService, private notificationService: NotificationService, private validationService: ValidationService) {
        this.i18n = I18nService.getInstance();
    }

    ngOnInit() {
        console.log('BrdModalComponent ngOnInit');
        this.setFormGroup();
    }

    setFormGroup(user?: User) {
        this.formGroup = new FormGroup({
            // birth: new FormControl('', [
            //     Validators.required,
            //     Validators.minLength(2),
            //     Validators.maxLength(100)
            // ]),
            nm: new FormControl(user ? user.nm : '', [Validators.required]),
            genCd: new FormControl(user ? user.genCd : '', [Validators.required]),
            birdt: new FormControl((user && user.birdt) ? moment(user.birdt).format('YYYY.MM.DD') : '', [Validators.required]),
            cpon: new FormControl(user ? user.cpon : '', [Validators.required, this.validationService.getPhoneValidatorFn()]),
            gadNm: new FormControl(user ? user.gadNm : '', [Validators.required]),
            gadRltspCd: new FormControl(user ? user.gadRltspCd : '', [Validators.required]),
            gadCpon: new FormControl(user ? user.gadCpon : '', [Validators.required, this.validationService.getPhoneValidatorFn()]),
            gadEmail: new FormControl(user ? user.gadEmail : '', [Validators.required, this.validationService.getEmailValidatorFn()]),
            ptcpGrpCd: new FormControl(user ? user.ptcpGrpCd : '', [Validators.required]),
            ptcpCd: new FormControl(user ? user.ptcpCd : '', [Validators.required]),
            ptcpStDt: new FormControl((user && user.ptcpStDt) ? moment(user.ptcpStDt).format('YYYY.MM.DD') : '', [Validators.required]),
            ptcpEndDt: new FormControl((user && user.ptcpEndDt) ? moment(user.ptcpEndDt).format('YYYY.MM.DD') : '', [Validators.required]),
            cont: new FormControl(user ? user.cont : ''),

        });
    }

    public show(detail = {} as User) {
        // if (detail) {
        //     detail['birdtFormat'] = moment(detail.birdt).format('YYYY.MM.DD');
        // }
        if (!detail.genCd) {
            detail.genCd = this.i18n.getCodes('GEC')[0].cd as GenderCode;
        }
        if (!detail.gadRltspCd) {
            detail.gadRltspCd = this.i18n.getCodes('PER')[0].cd as ParentCd;
        }
        if (!detail.ptcpGrpCd) {
            detail.ptcpGrpCd = this.i18n.getCodes('PTC')[0].cd as PtcpGrpCd;
        }
        if (!detail.ptcpCd) {
            detail.ptcpCd = this.i18n.getCodes('PTP')[0].cd as PtcpCd;
        }
        this.setFormGroup(detail);
        this.detail = detail;
        this.bsModalRef = this.modalService.show(this.brdTemplate);
    }

    // public hide() {
    //     this.bsModalRef && this.bsModalRef.hide();
    // }

    public create() {
        if (this.formGroup.valid) {
            const data = Object.assign({}, this.formGroup.value);
            if (this.detail.birdt) {
                data['birdt'] = moment(this.detail.birdt).toISOString()
            }
            if (this.detail.ptcpStDt) {
                data['ptcpStDt'] = moment(this.detail.ptcpStDt).toISOString()
            }
            if (this.detail.ptcpEndDt) {
                data['ptcpEndDt'] = moment(this.detail.ptcpEndDt).toISOString()
            }
            const action = new ApiAction(this.router.url);
            action.param = data;
            action.success = (_) => this.closeAndComplet();
            this.jsonApiService.post(action);
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    private closeAndComplet() {
        this.notificationService.successAlert(I18nService.getInstance().getTranslation('word.success'), I18nService.getInstance().getTranslation('msg.success'));
        this.complete.emit();
        this.bsModalRef.hide();
    }

    public update() {
        console.log(this.formGroup.value.birdt);
        let translations = I18nService.getInstance().getTranslation('Update');
        if (this.formGroup.valid && this.detail) {
            this.notificationService.smartMessageBox({
                title: I18nService.getInstance().getTranslation('Update'),
                content: I18nService.getInstance().getTranslation('UpdateMsg'),
                buttons: `[${I18nService.getInstance().getTranslation('word.ok')}][${I18nService.getInstance().getTranslation('word.cancel')}]`
            }, (ButtonPressed) => {
                if (ButtonPressed == I18nService.getInstance().getTranslation('word.ok')) {
                    const data = Object.assign(Object.assign({}, this.detail), this.formGroup.value);
                    if (this.detail.birdt) {
                        data['birdt'] = moment(this.detail.birdt).toISOString()
                    }
                    if (this.detail.ptcpStDt) {
                        data['ptcpStDt'] = moment(this.detail.ptcpStDt).toISOString()
                    }
                    if (this.detail.ptcpEndDt) {
                        data['ptcpEndDt'] = moment(this.detail.ptcpEndDt).toISOString()
                    }
                    const action = new ApiAction(this.router.url);
                    action.param = data;
                    action.success = (_) => this.closeAndComplet();
                    this.jsonApiService.put(action);
                }
            });
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    public delete() {
        if (this.detail && this.detail.userSeq) {
            this.notificationService.smartMessageBox({
                    title: I18nService.getInstance().getTranslation("word.delete"),
                    content: I18nService.getInstance().getTranslation("msg.delete"),
                    buttons: `[${I18nService.getInstance().getTranslation('word.ok')}][${I18nService.getInstance().getTranslation('word.cancel')}]`
                },
                (ButtonPressed) => {
                    if (ButtonPressed == I18nService.getInstance().getTranslation("word.ok")) {
                        const action = new ApiAction(this.router.url);
                        action.param = {
                            userSeq: this.detail.userSeq
                        };
                        action.success = (_) => this.closeAndComplet();
                        this.jsonApiService.delete(action);
                    }
                });
        }
    }

    changeBirtDt(data: Date | any | Event) {
        if (data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = '';
                this.formGroup.get('birdt').setValue('');
                data = null;
            }
        } else {
            this.formGroup.get('birdt').setValue(moment(data).format('YYYY.MM.DD'));
        }
        this.detail.birdt = data;
    }

    changePtcpStDt(data: Date | any | Event) {
        if (data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = '';
                this.formGroup.get('ptcpStDt').setValue('');
                data = null;
            }
        } else {
            this.formGroup.get('ptcpStDt').setValue(moment(data).format('YYYY.MM.DD'));
        }
        this.detail.ptcpStDt = data;
    }

    changePtcpEndDt(data: Date | any | Event) {
        if (data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = '';
                this.formGroup.get('ptcpEndDt').setValue('');
                data = null;
            }
        } else {
            this.formGroup.get('ptcpEndDt').setValue(moment(data).format('YYYY.MM.DD'));
        }
        this.detail.ptcpEndDt = data;
    }
}
