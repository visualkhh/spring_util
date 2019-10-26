import {Injectable} from "@angular/core";

import {BehaviorSubject} from "rxjs";
import {JsonApiService} from "@app/core/services/json-api.service";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {ApiAction} from "@app/core/services/index";
import {Router} from "@angular/router";
import {UseCd, UserDetails} from "@app/model/commomModels";
import { CookieService } from 'ngx-cookie-service';


const defaultUser = {
    admNm: 'Guest',
    useCd: 'USE002'
} as UserDetails;

@Injectable()
export class UserService {
    // private _userDetails: UserDetails;
    public user$ = new BehaviorSubject<UserDetails>(Object.assign({}, defaultUser));
    public session = {} as any;
    constructor(private jsonApiService: JsonApiService, private httpClient: HttpClient, private cookieService: CookieService, private router: Router) {
        // console.log('UserService onstructor')
        // this.jsonApiService.fetch("/user/login-info.json").subscribe(this.user$)
        // this.httpClient.get<UserDetails>('/auth/detail').subscribe(this.user$)
        // const action = new ApiAction<UserDetails>('/auth/detail');
        // action.success = (at: UserDetails) => {
        //     this.user$.next(at);
        // };
        // this.jsonApiService.get(action);
          // this.httpClient.get<UserDetails>('/auth/detail');
        this.reloadUserDetails();
    }

    // get userDetails(): UserDetails {
    //     return this._userDetails;
    // }

// public lastUserDetails(): UserDetails {
    //     return this.user$['_value'];
    // }
    public next(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }
    public setUserDetails(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }

    get userDetails() {
        return this.user$.getValue();
    }
    public getAuths(level: number, prntUrlSeq?: number, method = 'GET') {
        if (this.userDetails && this.userDetails.auths) {
            return this.userDetails.auths.filter(it => level === it.menuLvl && (prntUrlSeq ? it.prntUrlSeq === prntUrlSeq: true) && method === it.crudTypeCd && 'Y' === it.useYn && 'N' === it.hddnYn);
        }
    }
    
    public isAceept() {
        const userDetails = this.user$.getValue();
        return userDetails && 'USE001' === userDetails.useCd;
    }
    public isAcceptUrl(url: string, ...crud: string[]): boolean {
        const userDetails = this.user$.getValue();
        if ('USE001' === userDetails.useCd && userDetails.auths && userDetails.auths.length > 0 ) {
            for (let i = 0; i < userDetails.auths.length; i++) {
                for (var y = 0 ; y < crud.length; y++) {
                    if ((url === userDetails.auths[i].url || new RegExp(userDetails.auths[i].url).test(url))  && crud[y] === userDetails.auths[i].crudTypeCd) {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }
    // public isAcceptIncludesUrl(url: string, ...crud: string[]): boolean {
    //     const userDetails = this.user$.getValue();
    //     if ('USE001' === userDetails.useCd && userDetails.auths && userDetails.auths.length > 0 ) {
    //         for (let i = 0; i < userDetails.auths.length; i++) {
    //             for (var y = 0 ; y < crud.length; y++) {
    //                 if (url.includes(userDetails.auths[i].url) && crud[y] === userDetails.auths[i].crudTypeCd) {
    //                     return true;
    //                 }
    //             }
    //         }
    //     } else {
    //         return false;
    //     }
    //     return false;
    // }
    // public userSubscribe(next?: (value: UserDetails) => void, error?: (error: any) => void, complete?: () => void) {
    //     this.user$.subscribe(next, error, complete);
    // }
    // public getUserDetails(): Observable<UserDetails> {
    //     const observable = this.httpClient.get<UserDetails>('/auth/detail');
    //     observable.subscribe( (it) => this._userDetails = it);
    //     return observable;
    // }
    // public login(param: any, success?: (data: UserDetails) => void) {
    //     this.jsonApiService.post<UserDetails>({
    //         url: '/security/sign-in',
    //         param: param,
    //         success: (it: UserDetails) => {
    //             it = Object.assign(new UserDetails(), it);
    //             this.user$.next(it);
    //             // console.log('login user ' + it.admNm);
    //             // console.log('login user ' + it.isAccountNonLocked());
    //             // console.log('login user user& ' + this.user$.getValue());
    //             // this._userDetails = it;
    //             if(success) {
    //                 success(it);
    //             }else {
    //                 this.router.navigate(['/bbs']);
    //             }
    //         }
    //     } as ApiAction<UserDetails>);
    // }
    public reloadUserDetails() {
        this.jsonApiService.get<UserDetails>({
            // url: '/auth/detail',
            url: '/anon/userDetails',
            // param: {routerUrl: this.router.url},
            // param: {type: 'details'},
            success: (it: UserDetails) => {
                it = Object.assign({}, it);
                this.user$.next(it);
            },
            error: (at: HttpErrorResponse) => {
            },
            complete: () => {
                //console.log('sds')
            }
        } as ApiAction<UserDetails>);
    }
    // public logout(): Observable<Msg<any>>  {
    //     const action = new ApiAction('/security/sign-out');
    //     this.jsonApiService.get(action);
    //     const a = this.httpClient.get<Msg<any>>('/security/sign-out');
    //     this.user$.next(defaultUser as UserDetails);
    //     return a;
    // }
    // public logoutAndForWard() {
    //     this.jsonApiService.post<Msg<any>>({
    //         url: '/security/sign-out',
    //         success: (_: any) => {
    //             location.href = '/';
    //         }
    //     } as ApiAction<Msg<any>>);
    // }
}
