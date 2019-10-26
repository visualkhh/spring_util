import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {ApiAction, UserService} from "@app/core/services";
import {JsonApiService} from "@app/core/services/json-api.service";
import {FormGroup, FormControl, Validators} from '@angular/forms'
import {it} from "@angular/core/testing/src/testing_internal";
import {CookieService} from "ngx-cookie-service";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {LocationStrategy, PathLocationStrategy} from "@angular/common";
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
    private type: string;

    constructor(private router: Router, private userService: UserService, private cookieService: CookieService, private http: HttpClient, private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.type = this.route.snapshot.queryParamMap.get('type');
        if ('sign-fail' === this.type) {
            this.note = 'msg.error.login.fail';
        }else if ('sign-out' === this.type) {
            this.note = 'msg.error.login.logout';
        }
        this.formdata = new FormGroup({
            username: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
            _csrf: new FormControl(this.cookieService.get("XSRF-TOKEN"), [Validators.required])
        });
        this.userService.user$.subscribe((it) => {
            console.log('login init  ' + it.admNm);
            if (it && 'USE001' === it.useCd) {
                location.href = it.homeUrl;
                // console.log(location.href, it.homeUrl);
                // this.router.navigate(['/bbs']);
                // this.router.navigate([it.homeUrl]);
            }
        });
    }

    submit(data) {
        const f: HTMLFormElement = this.someInput.nativeElement;
        f.submit();
        // if (this.formdata.valid) {
        //     f.submit();
        // }
    }

}
