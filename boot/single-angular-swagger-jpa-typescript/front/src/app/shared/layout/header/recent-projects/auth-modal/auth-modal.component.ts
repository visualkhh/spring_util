import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {NotificationService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {UserDetails} from "@app/model/commomModels";

@Component({
    selector: 'auth-modal',
    templateUrl: './auth-modal.component.html',
})
export class AuthModalComponent {
    @Input() data: UserDetails;
    @Output() agree = new EventEmitter();
    @Output() close = new EventEmitter();
    @ViewChild('admLginPw') admLginPw: ElementRef;
    @ViewChild('admLginPwc') admLginPwc: ElementRef;
    infoForm: FormGroup;

    constructor(private valid: ValidationService, private i18: I18nService, private noti: NotificationService) {

        this.infoForm = new FormGroup({
            admSeq: new FormControl('', [
                Validators.required
            ]),
            email: new FormControl('', []),
            admLginPw: new FormControl('', []),
            admLginPwc: new FormControl('', []),
            admNm: new FormControl('', [
                Validators.required
            ]),
            useYn: new FormControl('', [
                Validators.required
            ])
        });
    }


    update(data: UserDetails) {
        if (this.infoForm.valid) {
            if (this.admLginPw.nativeElement.value === this.admLginPwc.nativeElement.value) {
                this.agree.emit(data);
            } else {
                this.noti.dangerAlert(this.i18.getTranslation('Password'), this.i18.getTranslation('ValidError'));
                return;
            }
        } else {
            this.valid.validCheck(this.infoForm);
        }
    }
}
