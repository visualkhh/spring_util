// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
// import {Router} from "@angular/router";
// import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '../../../../../../../thesis/thesis-angular/node_modules/@angular/core';
import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@target_angular_project/@angular/core';
// import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {PolicyInfo} from '@web-core-generate/models';
import {AlertService, I18nService, JsonApiService} from '@web-core/app/services';
// import {BsModalRef, BsModalService} from "@target/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "../../../../../../../thesis/thesis-angular/node_modules/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "@tar /ngx-bootstrap/modal";

@Component({
    selector: 'web-core-policies-confirm-modal',
    templateUrl: './policies-confirm-modal.component.html',
    styleUrls: ['./policies-confirm-modal.component.css']
})
export class PoliciesConfirmModalComponent implements OnInit {

    constructor(private i18n: I18nService, private alertService: AlertService, private modalService: BsModalService, private api: JsonApiService) {
    }
    @ViewChild('modalTemplate') modalTemplate;
    bsModalRef: BsModalRef;
    policy: PolicyInfo = {plcyCont: ''} as PolicyInfo;
    terms: PolicyInfo = {plcyCont: ''} as PolicyInfo;
    @Input()
    public config = {
        class: 'modal-lg',
        // ignoreBackdropClick: true
    };

    @Output() complete = new EventEmitter();

    // public show<T>(detail: T) {
    //   this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    // }
    confirm: {terms: boolean, policy: boolean} = {terms: false, policy: false};

    ngOnInit(): void {
    }

    public show() {
        this.policy = {plcyCont: ''} as PolicyInfo;
        this.terms = {plcyCont: ''} as PolicyInfo;
        this.confirm = {terms: false, policy: false};

        this.api.get<PolicyInfo[]>('/anons-web-core/policies').subscribe(it => {
            this.policy = it.find(sit => 'POL001' === sit.plcyCd) || this.policy;
            this.terms = it.find(sit => 'POL002' === sit.plcyCd) || this.terms;
            this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
        }, this.api.errorHandler.bind(this.api));
    }

    private closeAndComplet() {
        this.complete.emit();
        this.bsModalRef.hide();
    }

    confirmCheckAndClose() {
        if (this.confirm?.policy && this.confirm?.terms) {
            this.closeAndComplet();
        } else {
            if (!this.confirm?.policy) {
                this.alertService.dangerAlert(this.i18n.getTranslation('Policy') + ' ' + this.i18n.getTranslation('Agree'), this.i18n.getTranslation('Valid_required'));
            }
            if (!this.confirm?.terms) {
                this.alertService.dangerAlert(this.i18n.getTranslation('Terms') + ' ' + this.i18n.getTranslation('Agree'), this.i18n.getTranslation('Valid_required'));
            }
        }
    }

    close() {
        this.bsModalRef.hide();
    }
}
