import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";

import {config} from '@app/core/smartadmin.config';
import { Observable } from 'rxjs';
import {delay, map, catchError} from 'rxjs/operators';
import {NotificationService} from "@app/core/services/notification.service";
import {Router} from "@angular/router";
import {MsgCode} from "@app/code/MsgCode";
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class JsonApiService {


    constructor(private http: HttpClient, private router: Router, private cookieService: CookieService, private notificationService: NotificationService) {
    }

    public fetch(url): Observable<any>{
      return this.http.get(this.getBaseUrl() + config.API_URL + url)
      .pipe(
        delay(100),
        map((data: any)=>(data.data|| data)),
        catchError(this.handleError)
      )
    }

    private getBaseUrl(){
      return location.protocol + '//' + location.hostname + (location.port ? ':'+location.port : '') + '/'
    }

    private handleError(error:any) {
      // In a real world app, we might use a remote logging infrastructure
      // We'd also dig deeper into the error to get a better message
      let errMsg = (error.message) ? error.message :
        error.status ? `${error.status} - ${error.statusText}` : 'Server error';
      console.error(errMsg); // log to console instead
      return Observable.throw(errMsg);
    }



    //https://www.techiediaries.com/angular-http-client/
    //https://www.techiediaries.com/angular-http-client/
    // public get<T>(url: string, success:(data: T|any) => void, param = {}) {
    public get<T>(action: ApiAction<T>): Observable<T> {
        // this.http.get(url + "?" + ConvertUtil.objToGetURL(param))
        //const params = new HttpParams().set('_page', "1").set('_limit', "1");
        //const params = new HttpParams({fromString: '_page=1&_limit=1'});
        // const httpParams = new HttpParams().set('a', '4444').set('zzzz','ggg');
        // for (const key in param) {
        //     httpParams.set(key, param[key]+'');
        // }
        const options = {};
        if (action.param) {
            options['params'] = Object.getOwnPropertyNames(action.param)
            .reduce((p, key) => {
                const v = action.param[key];
                if(null !== v && undefined !== v){
                    return p.set(key, action.param[key]);
                }else {
                    return p;
                }
            }, new HttpParams());
        }
        if (action.headers) {
            options['headers'] = action.headers;
        }
       const pro = this.notificationService.progressSpinnerOpen();
       const observable = this.http.get<T>(action.url, options);
        // .catch((e: any) => {
        //     console.log('ee1');
        //     return <any>{}
        // })
        observable.subscribe(
                (data: any) => {
                    pro.out();

                    if (action.beforeSuccess) {
                        action.beforeSuccess(data);
                    }

                    if (action.success){
                        action.success(data);
                    }else {
                        this.httpClientHandleSuccess(data||{code: 'R00000'});
                    }
                    if (data && data.href) {
                        location.href = data.href;
                    }
                    if (data && data.location) {
                        this.router.navigate([data.location]);
                    }
                },
                (err: HttpErrorResponse) => {
                    pro.out();
                    if(err.status === 200 && err.url){
                        location.href = err.url;
                        return;
                    }
                    if (action.error) {
                        action.error(err);
                    } else {
                        this.httpClientHandleError(err.error||{code: 'E99999'});
                    }
                    if (action.afterComplete) {
                        action.afterComplete()
                    } else {
                        if (err.error.href) {
                            location.href = err.error.href;
                        }
                        if (err.error.location) {
                            this.router.navigate([err.error.location]);
                        }
                    }
                },
                () => {
                    //pro.out();
                    if (action.complete)
                        action.complete();
                }
            );
        return observable;
    }
    public delete<T>(action: ApiAction<T>): Observable<T> {
        const options = {};
        action.param = action.param || {};
        action.param['_csrf'] = this.getCSRF();
        if (action.param) {
            options['params'] = Object.getOwnPropertyNames(action.param)
            .reduce((p, key) => {
                if(action.param[key]){
                    return p.set(key, action.param[key]);
                }else {
                    return p;
                }
            }, new HttpParams());
        }
        if (action.headers) {
            options['headers'] = action.headers;
        }
        const pro = this.notificationService.progressSpinnerOpen();
        const observable = this.http.delete<T>(action.url, options);
        observable.subscribe(
                (data: any) => {
                    pro.out();
                    if (action.beforeSuccess) {
                        action.beforeSuccess(data);
                    }
                    if (action.success){
                        action.success(data);
                    }else {
                        this.httpClientHandleSuccess(data||{code: 'R00000'});
                    }
                    if (data && data.href) {
                        location.href = data.href;
                    }
                    if (data && data.location) {
                        this.router.navigate([data.location]);
                    }
                },
                (err: HttpErrorResponse) => {
                    pro.out();
                    if(err.status === 200 && err.url){
                        location.href = err.url;
                        return;
                    }
                    if (action.error) {
                        action.error(err);
                    } else {
                        this.httpClientHandleError(err.error||{code: 'E99999'});
                    }
                    if (action.afterComplete) {
                        action.afterComplete()
                    } else {
                        if (err.error.href) {
                            location.href = err.error.href;
                        }
                        if (err.error.location) {
                            this.router.navigate([err.error.location]);
                        }
                    }
                },
                () => {
                    //pro.out();
                    if (action.complete)
                        action.complete();
                }
            );
        return observable;
    }
    public post<T>(action: ApiAction<T>): Observable<T> {
        const options = {};
        action.param = action.param || {};
        if (action.param instanceof FormData) {
            action.url += "?_csrf=" + this.getCSRF();
        } else {
           action.param['_csrf'] = this.getCSRF();
        }
        if (action.headers) {
            options['headers'] = action.headers;
        }
        const pro = this.notificationService.progressSpinnerOpen();
        const observable = this.http.post<T>(action.url, action.param, options);
        const s = observable.subscribe(
                (data: any) => {
                    pro.out();
                    if (action.beforeSuccess) {
                        action.beforeSuccess(data);
                    }
                    if (action.success){
                        action.success(data);
                    }else {
                        this.httpClientHandleSuccess(data||{code: 'R00000'});
                    }
                    if (data && data.href) {
                        location.href = data.href;
                    }
                    if (data && data.location) {
                        this.router.navigate([data.location]);
                    }
                },
                (err: HttpErrorResponse) => {
                    pro.out();
                    if(err.status === 200 && err.url){
                        location.href = err.url;
                        return;
                    }
                    // if(err.error === 403) { //Forbidden
                    //
                    // }
                    if (action.error) {
                        action.error(err);
                    } else {
                        this.httpClientHandleError(err.error||{code: 'E99999'});
                    }
                    if (action.afterComplete) {
                        action.afterComplete()
                    } else {
                        if (err.error.href) {
                            location.href = err.error.href;
                        }
                        if (err.error.location) {
                            this.router.navigate([err.error.location]);
                        }
                    }
                },
                () => {
                    //pro.out();
                    if (action.complete)
                        action.complete();
                }
            );

        return observable;
    }
    public put<T>(action: ApiAction<T>): Observable<T> {
        const options = {};
        action.param = action.param || {};
        if (action.param instanceof FormData) {
            action.url += "?_csrf=" + this.getCSRF();
        } else {
            action.param['_csrf'] = this.getCSRF();
        }
        if (action.headers) {
            options['headers'] = action.headers;
        }
        const pro = this.notificationService.progressSpinnerOpen();
        const observable = this.http.put<T>(action.url, action.param, options);
        observable.subscribe(
                (data: any) => {
                    pro.out();
                    if (action.beforeSuccess) {
                        action.beforeSuccess(data);
                    }
                    if (action.success){
                        action.success(data);
                    }else {
                        this.httpClientHandleSuccess(data||{code: 'R00000'});
                    }
                    if (data && data.href) {
                        location.href = data.href;
                    }
                    if (data && data.location) {
                        this.router.navigate([data.location]);
                    }
                },
                (err: HttpErrorResponse) => {
                    pro.out();
                    if(err.status === 200 && err.url){
                        location.href=err.url;
                    }
                    if (action.error) {
                        action.error(err);
                    } else {
                        this.httpClientHandleError(err.error||{code: 'E99999'});
                    }
                    if (action.afterComplete) {
                        action.afterComplete()
                    } else {
                        if (err.error.href) {
                            location.href = err.error.href;
                        }
                        if (err.error.location) {
                            this.router.navigate([err.error.location]);
                        }
                    }
                },
                () => {
                    //pro.out();
                    if (action.complete)
                        action.complete();
                }
            );
        return observable;
    }

    public httpClientHandleSuccess(data: any = {}) {
        const code = data.code || 'R00000';
        const message = data.message || MsgCode.R00000;
        this.notificationService.successAlert(code, message);

    }
    public httpClientHandleError(error: any = {}) {
        const code = error.code || 'E99999';
        const message = error.message || MsgCode.E99999;
        this.notificationService.dangerAlert(code, message);
    }

    public getCSRF(): string{
        // return document.querySelector('meta[name="_csrf"]').getAttribute('content');
        return this.cookieService.get("XSRF-TOKEN");
    }

}

export  class ApiAction<T> {
    public url: string;
    public param: any;
    public headers: HttpHeaders;
    public success:(data: T|any) => void;
    public error:(err: any) => void;
    public complete:() => void;
    public beforeSend:(data: T|any) => void;
    public beforeSuccess:(data: T|any) => void;
    public afterComplete:() => void;

    constructor(url: string) {
        this.url = url;
    }
};
