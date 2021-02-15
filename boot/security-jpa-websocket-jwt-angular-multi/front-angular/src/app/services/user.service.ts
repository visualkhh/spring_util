import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {JsonApiService} from '@app/services/json-api.service';
import {AlertService} from '@app/services/ui/alert.service';
import {RxStompService, StompService} from '@stomp/ng2-stompjs';
import {com} from '@generate/models';
import User = com.clone.chat.domain.User;
import UserToken = com.clone.chat.model.UserToken;
import {UserTokenContain} from '@app/models/UserTokenContain';
import error = com.clone.chat.model.error;

declare let $: any;
declare let moment: any;

// export type AuthDetailsOption = { hddnCd?: UseCd, prntUrlSeq?: number, crudTypeCd?: CrudTypeCd };

const defaultUser = {
    id: 'Guest'
} as UserTokenContain;

// const defaultUser = userDetails;

@Injectable()
export class UserService { // implements CanActivate
    // https://git.fluig.com/projects/IN/repos/dependencies/browse/tools/projects/fluig-tools-lib/ngx-realtime/src/lib/ngx-realtime.service.ts?at=f355d44199b0a00dd7db631aad4e9cb8872f1a25&raw

    public user$ = new BehaviorSubject<UserTokenContain>(Object.assign({}, defaultUser) as UserTokenContain);

    constructor(private rxStompService: RxStompService, private http: HttpClient, private alertService: AlertService, private api: JsonApiService, private httpClient: HttpClient, private router: Router) {
        this.reloadUserDetails();
    }

    public next(userDetails: UserTokenContain) {
        this.user$.next(userDetails);
    }

    public setUserDetails(userDetails: UserTokenContain) {
        this.user$.next(userDetails);
    }

    get userDetails(): UserTokenContain {
        return this.user$.getValue();
    }

    public login(username: string, password: string) {
        const params = {username, password};
        this.api.post<UserToken>('/securitys/user-sign-in', {params}).subscribe((it: UserToken) => {
            window.localStorage.setItem('token', it.token);
            this.reloadUserDetails();
            
        }, (error) => {
                this.alertService.dangerAlertHttpErrorResponse('error', '계정정보를 확인해주세요');
            }
        // this.api.errorHandler.bind(this.api)
            ,
            
        );
    }

    public reloadUserDetails() {
        const token = window.localStorage.getItem('token');
        if (undefined === token || null === token || token.length <= 0) {
            return;
        }
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append('Authorization', token);

        this.api.post<UserToken>('/apis/auths/details', {headers}).subscribe((it: UserToken) => {
            it = Object.assign({}, it);
            if (it.authorities && it.authorities.length > 0) {
                this.user$.next(Object.assign(new UserTokenContain(), it));
                // this.stompService.subscribe('/user/queue/friends').subscribe((msg) => {
                //     console.log(msg);
                // }, error => {
                //     console.log(error);
                // })
                // this.rxStompService.watch('/user/queue/friends',{'Authorization': token});
            } else {
                this.router.navigate(['/login'], {queryParams: {type: 'sign-out'}});
            }
        }, (err: HttpErrorResponse) => {
            this.api.errorHandler(err);
            this.router.navigate(['/login'], {queryParams: {type: 'sign-out'}});
        });
    }

    public logout() {
        window.localStorage.removeItem('token');
        // location.href = '/';
    }



}
