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
import {HttpClient} from '@angular/common/http';
import {UserService} from '@app/services/user.service';
import {JsonApiService} from '@app/services/json-api.service';
import {AlertService} from '@app/services/ui/alert.service';
import {MomentService} from '@app/services/date/moment.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CookieService} from 'ngx-cookie-service';
import {filter} from 'rxjs/operators';
declare var $;

@Component({
    selector: 'app-login',
    // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
    templateUrl: './sign-up.component.html',
    styleUrls: ['./sign-up.component.css']
})
export class LoginComponent implements OnInit {
    form: any = Object();
    note: string;
    username: string;
    password: string;
    formdata: FormGroup;
    @ViewChild('from') someInput: ElementRef;
    private type: string;

    private forceProfile: string;

    // https://stackoverflow.com/questions/38093727/angular2-insert-a-dynamic-component-as-child-of-a-container-in-the-dom
    constructor(private router: Router, private userService: UserService, private cookieService: CookieService,
                private http: HttpClient, private route: ActivatedRoute, private api: JsonApiService, private alertService: AlertService,
                private momentService: MomentService, private renderer2: Renderer2, private el: ElementRef, private componentFactoryResolver: ComponentFactoryResolver,
                public viewContainerRef: ViewContainerRef,
    ) {
        // console.log('LoginComponent constructor');
        // setTimeout(() => {
        //     this.sw = true;
        // }, 2000);

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

        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe(sit => {
            this.router.navigate(['/home']);
        });

        this.userService.user$.subscribe((it) => {
            // console.log('login init  ' + it.admNm);
            // if (UseCd.USE001 === it.useCd) {
            //     if (it.homeUrl) {
            //         this.router.navigate([it.homeUrl.replace('/#', '')]);
            //     } else {
            //         this.router.navigate(['/home']);
            //     }
            // }
        });

    }

    submit(e) {
        this.userService.login(this.username, this.password);
    }



    policiesComplete() {
        // this.signUpModal.show();
    }

    showFindIdPwd() {
        // this.idpwdFindModal.show();
    }
}
