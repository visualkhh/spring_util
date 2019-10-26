import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ApiAction, JsonApiService, UserService} from "@app/core/services";
import {NotificationService} from "@app/core/services/notification.service";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {ValidationService} from "@app/core/services/validation.service";
import {Brd, BrdCateCd, UserDetails} from "@app/model/commomModels";
import {I18nPipe} from "@app/shared/i18n/i18n.pipe";

declare let $: any;

@Component({
    selector: 'app-brd-modal',
    templateUrl: './brd-modal.component.html',
    styleUrls: ['./brd-modal.component.css'],
})

export class BrdModalComponent implements OnInit {

    @ViewChild('brdModalTemplate') brdTemplate;
    bsModalRef: BsModalRef;
    detail = {} as Brd;
    cateCd: BrdCateCd;
    formGroup: FormGroup;
    @Output() complete = new EventEmitter();
    // userDetails: UserDetails;

    constructor(private router: Router, private modalService: BsModalService, private jsonApiService: JsonApiService, public userService: UserService, private notificationService: NotificationService, private valid: ValidationService) {
    }

    ngOnInit() {
        console.log('BrdModalComponent ngOnInit');
        this.formGroup = new FormGroup({
            titl: new FormControl('', [
                Validators.required,
                Validators.maxLength(100)
            ]),
            cont: new FormControl('', [
                Validators.required,
                Validators.maxLength(4000)
            ])
        });
    }

    public show(detail?: Brd) {
        this.detail = detail;
        this.bsModalRef = this.modalService.show(this.brdTemplate);
    }

    // public hide() {
    //     this.bsModalRef && this.bsModalRef.hide();
    // }

    public create() {
        if (this.formGroup.valid) {
            const data = Object.assign({}, this.formGroup.value) as Brd;
            data.cateCd = this.cateCd;
            const action = new ApiAction(this.router.url);
            action.param = data;
            action.success = (_) => this.closeAndComplet();
            this.jsonApiService.post(action);
        } else {
            this.valid.validCheck(this.formGroup);
        }
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
                    const action = new ApiAction(this.router.url);
                    action.param = data;
                    action.success = (_) => this.closeAndComplet();
                    this.jsonApiService.put(action);
                }
            });
        } else {
            this.valid.validCheck(this.formGroup);
        }
    }

    public delete() {
        if (this.detail && this.detail.brdSeq) {
            this.notificationService.smartMessageBox({
                    title: I18nService.getInstance().getTranslation("word.delete"),
                    content: I18nService.getInstance().getTranslation("msg.delete"),
                    buttons: `[${I18nService.getInstance().getTranslation('word.ok')}][${I18nService.getInstance().getTranslation('word.cancel')}]`
                },
                (ButtonPressed) => {
                    if (ButtonPressed == I18nService.getInstance().getTranslation("word.ok")) {
                        const action = new ApiAction(this.router.url);
                        action.param = {
                            brdSeq: this.detail.brdSeq
                        };
                        action.success = (_) => this.closeAndComplet();
                        this.jsonApiService.delete(action);
                    }
                });
        } else {
            this.valid.validCheck(this.formGroup);
        }
    }

}
