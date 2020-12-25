import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {JsonApiService} from '@app/services/json-api.service';
import {AuthDepthDetail, AuthDetail, CrudTypeCd, UseCd, UserDetails} from '@generate/models';
import {I18nService} from '@app/services/i18n/i18n.service';
import {map} from 'rxjs/internal/operators/map';
import {AlertService} from '@app/services/ui/alert.service';
import * as url from 'url';

declare let $: any;
declare let moment: any;

export type AuthDetailsOption = { hddnCd?: UseCd, prntUrlSeq?: number, crudTypeCd?: CrudTypeCd };

const defaultUser = {
    admNm: 'Guest',
    useCd: UseCd.USE002
} as UserDetails;

// const defaultUser = userDetails;

@Injectable()
export class UserService implements CanActivate {

    public user$ = new BehaviorSubject<UserDetails>(Object.assign({}, defaultUser) as UserDetails);

    constructor(private i18n: I18nService, private http: HttpClient, private alertService: AlertService, private api: JsonApiService, private httpClient: HttpClient, private router: Router) {
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
        this.api.get<UserDetails>('/anons/userDetails').subscribe((it: UserDetails) => {
            it = Object.assign({}, it);
            if (UseCd.USE001 === it.useCd) {
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

    public getAuths(url: string, ...crud: string[]): Observable<AuthDetail[]> {
        return this.api.get<AuthDetail[]>('/anons/auths', {params: {path: url, crudTypeCd: crud}});
    }

    public isAccept(url: string, crud: string): Observable<boolean> {
        return this.getAuths(url, crud)
            .pipe(
                map(it => {
                    return it && it.length > 0;
                })
            );
    }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        const urlPath = url.parse(state.url).pathname;
        return this.isAccept(urlPath, CrudTypeCd.GET).toPromise().then(it => {
            if (!it) {
                this.alertService.dangerAlert(this.i18n.getTranslation('ROLE'), this.i18n.getTranslation('msg.error.unauth.user'));
            }
            return it;
        }).catch(e => {
            this.alertService.dangerAlert(this.i18n.getTranslation('ROLE'), this.i18n.getTranslation('msg.error.unauth.user'));
            return false;
        });
    }

}
