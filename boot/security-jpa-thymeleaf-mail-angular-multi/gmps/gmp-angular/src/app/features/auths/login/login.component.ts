import {Component, ComponentFactoryResolver, ElementRef, OnInit, Renderer2, ViewChild, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '@web-core-app/services/user.service';
import {JsonApiService} from '@web-core-app/services/json-api.service';
import {AlertService} from '@web-core-app/services/ui/alert.service';
import {MomentService} from '@web-core-app/services/date/moment.service';
import {IdpwdFindModalComponent} from '@web-core-app/features/auths/find/modal/idpwd-find-modal.component';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Brd} from '@web-core-generate/models';
import {Page} from '@web-core-app/models/Page';
import {BrdModalComponent} from '@web-core-app/features/brds/modal/brd-modal.component';
import {SignUpModalComponent} from '@web-core-app/features/auths/signs/sign-up/modal/sign-up-modal.component';
import {CookieService} from 'ngx-cookie-service';
import {map} from 'rxjs/operators';
import {PoliciesConfirmModalComponent} from '@web-core/app/features/auths/signs/policies-confirm-modal/policies-confirm-modal.component';

@Component({
    selector: 'app-login',
    // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    form: any = Object();
    note: string;
    formdata: FormGroup;
    @ViewChild('from') someInput: ElementRef;
    @ViewChild(BrdModalComponent) modal: BrdModalComponent;
    @ViewChild(SignUpModalComponent) signUpModal: SignUpModalComponent;
    @ViewChild(PoliciesConfirmModalComponent) policiesConfirmModal: PoliciesConfirmModalComponent;
    @ViewChild(IdpwdFindModalComponent) idpwdFindModal: IdpwdFindModalComponent;
    private type: string;

    private forceProfile: string;
    private notices: Brd[];
    // @ViewChild('modal', {read: BrdModal}) viewContainer: ViewContainerRef;

    // https://stackoverflow.com/questions/38093727/angular2-insert-a-dynamic-component-as-child-of-a-container-in-the-dom
    constructor(private router: Router, private userService: UserService, private cookieService: CookieService,
                private http: HttpClient, private route: ActivatedRoute, private api: JsonApiService, private alertService: AlertService,
                private momentService: MomentService, private renderer2: Renderer2, private el: ElementRef, private componentFactoryResolver: ComponentFactoryResolver,
                public viewContainerRef: ViewContainerRef,
    ) {
        console.log('LoginComponent constructor');


        // const componentFactory = this.componentFactoryResolver.resolveComponentFactory(
        //     BasicModalComponent,
        // );
        // const viewRef = viewContainerRef.detach();
        // const componentRef = this.viewContainerRef.createComponent(componentFactory);
        // this.el.nativeElement.appendChild(componentRef.location.nativeElement);

        // const booleanObservable = of(true);
        // const ss = of(true);
        // booleanObservable.pipe(
        //         switchMapTo('zzz'),
        //         debounceTime(1),
        //         distinctUntilChanged(),
        //         takeUntil(ss)
        //     )
        //     .subscribe((value) => console.log('-----', value));
        //
        // ss.subscribe((o) => {
        //    console.log(o);
        // });
    }

    ngOnInit() {
        this.type = this.route.snapshot.queryParamMap.get('type');
        if ('sign-fail' === this.type) {
            this.note = 'msg.error.login.fail';
        } else if ('sign-out' === this.type) {
            this.note = 'msg.error.login.logout';
        }
        this.formdata = new FormGroup({
            username: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
            _csrf: new FormControl(this.cookieService.get('XSRF-TOKEN'), [Validators.required])
        });

        this.userService.user$.subscribe((it) => {
            console.log('login init  ' + it.admNm);
            if ('USE001' === it.useCd) {
                if (it.homeUrl) {
                    this.router.navigate([it.homeUrl.replace('/#', '')]);
                } else {
                    this.router.navigate(['/home']);
                }
            }
        });

        this.api.get<Page<Brd>>('/anons-web-core/notices', {params: {size: 3}}).pipe(
            map(it => it.content)
        ).subscribe(it => {
            this.notices = it;

        });
    }

    submit(data) {
        const f: HTMLFormElement = this.someInput.nativeElement;
        f.submit();
    }

    showBrdModal(brd: Brd) {
        this.modal.show(brd);
    }

    showPolicySignUp() {
        this.policiesConfirmModal.show();
    }

    policiesComplete() {
        this.signUpModal.show();
    }

    showFindIdPwd() {
        this.idpwdFindModal.show();
    }
}
