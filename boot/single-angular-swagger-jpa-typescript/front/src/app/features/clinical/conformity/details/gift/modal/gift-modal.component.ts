import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ApiAction, JsonApiService, UserService} from "@app/core/services";
import {NotificationService} from "@app/core/services/notification.service";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {ValidationService} from "@app/core/services/validation.service";
import {Brd, BrdCateCd, Gift, UserDetails} from "@app/model/commomModels";
import {I18nPipe} from "@app/shared/i18n/i18n.pipe";

declare let $: any;

@Component({
    selector: 'app-gift-modal',
    templateUrl: './gift-modal.component.html',
    styleUrls: ['./gift-modal.component.css'],
})

export class GiftModalComponent implements OnInit {

    @ViewChild('template') brdTemplate;
    bsModalRef: BsModalRef;
    detail = {} as Gift;
    formGroup: FormGroup;
    @Output() complete = new EventEmitter();
    private i18n: I18nService;

    constructor(private router: Router, private modalService: BsModalService, private jsonApiService: JsonApiService, public userService: UserService, private notificationService: NotificationService, private valid: ValidationService) {
        this.i18n = I18nService.getInstance();
    }

    ngOnInit() {
        console.log('GiftModalComponent ngOnInit');
        this.formGroup = new FormGroup({
            comCd: new FormControl('', [
                Validators.required
            ]),
            cont: new FormControl('', [
                Validators.required,
                Validators.maxLength(4000)
            ])
        });
    }

    public show(detail?: Gift) {
        this.detail = detail;
        this.bsModalRef = this.modalService.show(this.brdTemplate);
    }


    private closeAndComplet() {
        this.notificationService.successAlert(I18nService.getInstance().getTranslation('word.success'), I18nService.getInstance().getTranslation('msg.success'));
        this.complete.emit();
        this.bsModalRef.hide();
    }

    public update() {
        console.log(this.formGroup.value);
        let translations = I18nService.getInstance().getTranslation('Update');
        if (this.formGroup.valid && this.detail) {
            this.notificationService.smartMessageBox({
                title: I18nService.getInstance().getTranslation('Update'),
                content: I18nService.getInstance().getTranslation('UpdateMsg'),
                buttons: `[${I18nService.getInstance().getTranslation('word.ok')}][${I18nService.getInstance().getTranslation('word.cancel')}]`
            }, (ButtonPressed) => {
                if (ButtonPressed == I18nService.getInstance().getTranslation('word.ok')) {
                    const data = Object.assign(Object.assign({}, this.detail), this.formGroup.value);
                    const action = new ApiAction(this.router.url+'/gifts/'+this.detail.giftSeq);
                    action.param = data;
                    action.success = (_) => this.closeAndComplet();
                    this.jsonApiService.put(action);
                }
            });
        } else {
            this.valid.validCheck(this.formGroup);
        }
    }


}
