import {
    Component,
    ComponentFactoryResolver,
    ElementRef,
    OnInit,
    Renderer2,
    ViewChild,
    ViewContainerRef
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserService} from '@app/services/user.service';
import {JsonApiService} from '@app/services/json-api.service';
import {AlertService} from '@app/services/ui/alert.service';
import {MomentService} from '@app/services/date/moment.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CookieService} from 'ngx-cookie-service';
import {filter} from 'rxjs/operators';
import {BasicModalComponent, ButtonsClickType} from '@app/shareds/modals/basic-modal/basic-modal.component';
import {com} from '@generate/models';
import RequestSignUp = com.clone.chat.controller.api.anon.model.RequestSignUp;
import Error = com.clone.chat.model.error.Error;
import FieldError = com.clone.chat.model.error.FieldError;
import {ValidationService} from '@app/services/validation/validation.service';
declare var $;

@Component({
    selector: 'app-login',
    // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    form: any = Object();
    note: string;
    username: string;
    password: string;
    requestSignUp = new RequestSignUp();
    fileList: FileList;
    @ViewChild('from') someInput: ElementRef;
    @ViewChild('signUpModal') signUpModal: BasicModalComponent;
    private type: string;
    infoForm: FormGroup;
    // https://stackoverflow.com/questions/38093727/angular2-insert-a-dynamic-component-as-child-of-a-container-in-the-dom
    constructor(private validationService: ValidationService, private router: Router, private userService: UserService, private cookieService: CookieService,
                private http: HttpClient, private route: ActivatedRoute, private api: JsonApiService, private alertService: AlertService,
                private momentService: MomentService, private renderer2: Renderer2, private el: ElementRef, private componentFactoryResolver: ComponentFactoryResolver,
                public viewContainerRef: ViewContainerRef,
    ) {
        this.setFormGroup();
    }

    ngOnInit() {
        this.setFormGroup();
        this.requestSignUp = new RequestSignUp();
        this.type = this.route.snapshot.queryParamMap.get('type');
        if ('sign-fail' === this.type) {
            this.note = 'msg.error.login.fail';
        } else if ('sign-out' === this.type) {
            this.note = 'msg.error.login.logout';
        }


        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe(sit => {
            this.router.navigate(['/home']);
        });

        this.userService.user$.subscribe((it) => {
        });

    }


    setFormGroup() {
        this.infoForm = new FormGroup({
            id: new FormControl('', [Validators.required]),
            nickName: new FormControl('', [Validators.required]),
            phone: new FormControl('', [Validators.required]),
            statusMsg: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
            file: new FormControl('', [Validators.required]),
        });
    }

    submit(e) {
        this.userService.login(this.username, this.password);
    }

    signUp($event: ButtonsClickType) {
        if (!this.infoForm.valid){
            this.validationService.validCheck(this.infoForm);
            return;
        }
        const fromData = this.infoForm.value;
        const headers = new HttpHeaders();
        headers.append('Content-Type', 'multipart/form-data');
        const requestFormData = new FormData();
        Object.keys(fromData).filter(it => fromData[it]).forEach(it => {
            requestFormData.append(it, fromData[it]);
        });
        if (this.fileList !== undefined && this.fileList.length > 0){
            requestFormData.append('file', this.fileList[0]);
        }
        this.api.post<void>(
            `/anon-apis/sign-up`,
            {params: requestFormData, headers})
            .subscribe(_ => {
                this.signUpModal.close();
            }, (error => {
                console.log(error)
                if(error.error.code === 'ERROR_BIND') {
                    const err = error.error as Error<FieldError<string>>;
                    let details = '에러<br/> ';
                    err.errors.forEach((it: FieldError<string>) => {
                        details +=  it.message + '<br/>' + it.field;
                    });
                    this.alertService.dangerAlert(err.message, details);
                }
                else if(error.error.code === 'ERROR_DUPLICATED_ID')
                    this.alertService.dangerAlert('에러', '중복된 아이디입니다.');
            }));
    }

    openSignUpModal() {
        this.requestSignUp = new RequestSignUp();
        this.signUpModal.show();
        this.setFormGroup();
    }
}
