import {Injectable} from '@angular/core';
import {BehaviorSubject, pipe} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {JsonApiService} from '@app/services/json-api.service';
import {AlertService} from '@app/services/ui/alert.service';
import {RxStompService, StompService} from '@stomp/ng2-stompjs';
import {com} from '@generate/models';
import User = com.clone.chat.domain.User;
import Error = com.clone.chat.model.error.Error;
import UserToken = com.clone.chat.model.UserToken;
import {UserTokenContain} from '@app/models/UserTokenContain';

import {filter} from 'rxjs/operators';
import {UserService} from '@app/services/user.service';
import {of} from 'rxjs/internal/observable/of';
import {map} from 'rxjs/internal/operators/map';
import {Observable} from 'rxjs/index';
import {IRxStompPublishParams} from '@stomp/rx-stomp/esm6/i-rx-stomp-publish-params';

declare let $: any;
declare let moment: any;



@Injectable()
export class WsService { // implements CanActivate
    // public user$ = new BehaviorSubject<UserTokenContain>(Object.assign({}, defaultUser) as UserTokenContain);

    constructor(private rxStompService: RxStompService, private userService: UserService, private http: HttpClient, private alertService: AlertService, private api: JsonApiService, private httpClient: HttpClient, private router: Router) {
        this.init();
    }


    private init() {
        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe((sit: UserTokenContain) => {
            this.rxStompService.watch('/user/queue/errors', sit.authorizationHeaders).subscribe((wit) => {
                const error = JSON.parse(wit.body) as Error<any>;
                this.alertService.dangerAlert(error.code, error.message);
            });

            // this.rxStompService.watch('/topic/brodcast', sit.authorizationHeaders).subscribe((wit) => {
            //     console.log(wit.body);
            // });
        });
        // // ws.send("/app/friends", {'Authorization': token}, data);
        // const data = {
        //     id: 'test'
        // };
        // this.rxStompService.publish({destination: '/app/friends', body: JSON.stringify(data), headers: sit.authorizationHeaders});
    }

    public watch<T>(url: string): Observable<T> {
        return this.rxStompService.watch(url, this.userService.userDetails.authorizationHeaders).pipe(
            map(it => JSON.parse(it.body) as T)
        );
    }

    // endpoint /app...
    public publish<T>(url: string, data?: any): void {
        const pdata = {destination: url, headers: this.userService.userDetails.authorizationHeaders} as IRxStompPublishParams;
        if (data) {
            pdata.body =  JSON.stringify(data);
        }
        this.rxStompService.publish(pdata);
    }
}
