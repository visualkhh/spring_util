import {Component, EventEmitter, ElementRef, Input, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import {UserDetails} from '@web-core-generate/models';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
// import {LocationStrategy, PathLocationStrategy} from '@angular/common';
// import {Renderer} from '@angular/compiler-cli/ngcc/src/rendering/renderer';

@Component({
    selector: 'web-core-user-details',
    styleUrls: ['user-details.component.css'],
    templateUrl: './user-details.component.html',
    // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class UserDetailsComponent implements OnInit {

    @ViewChild('modalTemplate') modalTemplate;
    @Input() public class = '';
    @Input() public type: 'inline' | 'block' = 'inline';
    public userDetails: UserDetails;

    @Output() complete = new EventEmitter();
    // @ViewChild('admLginPw', {static: false}) admLginPw: ElementRef;
    // @ViewChild('admLginPw') admLginPw: ElementRef;
    // @ViewChild('admLginPwc') admLginPwc: ElementRef;
    // @ViewChild('caca') caca: ElementRef;
    infoForm: FormGroup;
    bsModalRef: BsModalRef;
    config = {
        class: 'modal-md',
        // class: 'modal-lg',
        ignoreBackdropClick: true
    };
    private oldUserDetails: UserDetails = {} as UserDetails;

    constructor(private modalService: BsModalService, private validationService: ValidationService,
                private cookieService: CookieService, private alertService: AlertService, private api: JsonApiService,
                private i18n: I18nService, public codeService: CodeService, private renderer: Renderer2) {
        console.log('UserDetailsComponent constructor');
        this.infoForm = new FormGroup({
            admSeq: new FormControl('', [Validators.required]),
            email: new FormControl('', [Validators.email]),
            admLginPw: new FormControl('', []),
            admLginPwc: new FormControl('', []),
            admNm: new FormControl('', [Validators.required]),
            homeUrl: new FormControl('', []),
            useCd: new FormControl('', [Validators.required])
        });
    }

    ngOnInit() {
        this.request();
    }

    close() {
        this.bsModalRef.hide();
    }

    update() {
        if (this.infoForm.valid) {
            const formData = this.infoForm.value;
            if (formData.admLginPw && formData.admLginPw !== formData.admLginPwc) {
                this.alertService.dangerAlert(this.i18n.getTranslation('Password'), this.i18n.getTranslation('ValidError'));
                this.renderer.selectRootElement('#admLginPw').focus();
                return;
            } else {
                this.api.put<any>('/auths-web-core/detail', {params: formData})
                    .subscribe((_) => {
                        this.close();
                        location.href = '/';
                    });
            }
        } else {
            this.validationService.validCheck(this.infoForm);
        }
    }

    no() {
        this.close();
    }

    request() {
        this.userDetails = undefined;
        this.oldUserDetails = undefined;
        this.api.get<UserDetails>('/auths-web-core/detail')
            .subscribe(data => {
                this.userDetails = data;
                this.oldUserDetails = Object.assign({}, this.userDetails);
            }, this.api.errorHandler.bind(this.api));
    }

    show() {
        this.request();
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    }

    private closeAndComplet() {
        this.complete.emit();
        this.bsModalRef.hide();
    }
}
