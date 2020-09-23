import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {AuthDepthDetail, AuthDetail, CrudTypeCd, UseCd, UserDetails} from '@web-core-generate/models';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {map} from 'rxjs/internal/operators/map';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import * as url from 'url';

declare let $: any;
declare let moment: any;

export type AuthDetailsOption = { hddnCd?: UseCd, prntUrlSeq?: number, crudTypeCd?: CrudTypeCd };

const defaultUser = {
    admNm: 'Guest',
    useCd: 'USE002'
} as UserDetails;

// const defaultUser = userDetails;

@Injectable()
export class UserService implements CanActivate {

    public user$ = new BehaviorSubject<UserDetails>(Object.assign({}, defaultUser) as UserDetails);

    constructor(private i18n: I18nService, private http: HttpClient, private alertService: AlertService, private api: JsonApiService, private httpClient: HttpClient, private router: Router) {
        console.log('UserService constructor');
        this.reloadUserDetails();
    }

    public next(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }

    public setUserDetails(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }

    get userDetails(): UserDetails {
        return this.user$.getValue();
    }

    public reloadUserDetails() {
        this.api.get<UserDetails>('/anons-web-core/userDetails').subscribe((it: UserDetails) => {
            it = Object.assign({}, it);
            if ('USE001' === it.useCd) {
                this.user$.next(it);
            } else {
                this.router.navigate(['/login'], {queryParams: {type: 'sign-out'}});
            }
        }, (err: HttpErrorResponse) => {
            this.api.errorHandler(err);
            this.router.navigate(['/login'], {queryParams: {type: 'sign-out'}});
        });

    }


    authDupDepthDetailsLoopFilter(filterCallback: (it: AuthDepthDetail) => boolean, adding: (it: AuthDepthDetail) => void = (_) => {
    }): AuthDepthDetail[] {
        if (this.userDetails.authDupDepthDetails) {
            const data = this.userDetails.authDupDepthDetails.slice().map(it => this.authDupDepthDetailsChildLoop(it, filterCallback, adding)).filter(it => it);
            return data;
        }
    }

    authDupDepthDetailsChildLoop(auth: AuthDepthDetail, filterCallback: (it: AuthDepthDetail) => boolean, adding: (it: AuthDepthDetail) => void = (_) => {
    }): AuthDepthDetail {
        auth = Object.assign({} as AuthDepthDetail, auth);
        if (auth.childs?.length > 0) {
            const childs = new Array<AuthDepthDetail>();
            auth.childs.forEach(it => {
                const authDepthDetail = this.authDupDepthDetailsChildLoop(it, filterCallback, adding);
                if (authDepthDetail) {
                    childs.push(authDepthDetail);
                }
            });
            auth.childs = childs;
            if (auth.childs.length <= 0 && filterCallback(auth)) {
                adding(auth);
                return auth;
            } else if (auth.childs.length > 0) {
                adding(auth);
                return auth;
            }
        } else if (filterCallback(auth)) {
            adding(auth);
            return auth;
        }
    }

    // public getAuthDetail(url: string, crud: string): AuthDetail | undefined {
    //     for (let i = 0; this.userDetails.authDetails && this.userDetails.authDetails.length > 0 && i < this.userDetails.authDetails.length; i++) {
    //         const authDetail = this.userDetails.authDetails[i];
    //         if (url === authDetail.url && crud === authDetail.crudTypeCd) {
    //             return authDetail;
    //         }
    //     }
    // }
    //
    // public isAccept(url: string, ...crud: string[]): boolean {
    //     if (this.userDetails.authDetails && this.userDetails.authDetails.length > 0) {
    //         // tslint:disable-next-line:prefer-for-of
    //         for (let i = 0; i < this.userDetails.authDetails.length; i++) {
    //             // tslint:disable-next-line:prefer-for-of
    //             for (let y = 0; y < crud.length; y++) {
    //                 if (url === this.userDetails.authDetails[i].url && crud[y] === this.userDetails.authDetails[i].crudTypeCd) {
    //                     return true;
    //                 }
    //             }
    //         }
    //     } else {
    //         return false;
    //     }
    //     return false;
    // }

    public getAuths(url: string, ...crud: string[]): Observable<AuthDetail[]> {
        return this.api.get<AuthDetail[]>('/anons-web-core/auths', {params: {path: url, crudTypeCd: crud}});
    }

    public isAccept(url: string, crud: string): Observable<boolean> {
        return this.getAuths(url, crud)
            .pipe(
                map(it => {
                    return it && it.length > 0;
                })
            );
    }

    // async canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        console.log('activate state:', state.url, url.parse(state.url).pathname);
        const urlPath = url.parse(state.url).pathname;
        return this.isAccept(urlPath, 'GET').toPromise().then(it => {
            if (!it) {
                this.alertService.dangerAlert(this.i18n.getTranslation('ROLE'), this.i18n.getTranslation('msg.error.unauth.user'));
            }
            return it;
        }).catch(it => {
            this.alertService.dangerAlert(this.i18n.getTranslation('ROLE'), this.i18n.getTranslation('msg.error.unauth.user'));
            return false;
        });
    }

}
