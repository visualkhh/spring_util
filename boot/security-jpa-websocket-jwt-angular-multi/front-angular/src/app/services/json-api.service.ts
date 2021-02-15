import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {ValidUtil} from '@app/utils/ValidUtil';
import {AlertService} from '@app/services/ui/alert.service';
import {Observable} from 'rxjs';
import {catchError, delay, map, mergeMap, finalize} from 'rxjs/operators';
import {Router} from '@angular/router';
import {MsgCode} from '@app/shareds/code/MsgCode';
import {CookieService} from 'ngx-cookie-service';
import {Injectable, Injector} from '@angular/core';
import {of} from 'rxjs/internal/observable/of';

export type HttpOption = {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
};

@Injectable()
export class JsonApiService {

    // private i18n: I18nService;

    constructor(private http: HttpClient, private injector: Injector, private router: Router,
                private cookieService: CookieService, private alertService: AlertService) {
        // this.i18n = injector.get(I18nService)
        // setTimeout(() => {
        //     this.i18n = this.injector.get(I18nService);
        //     console.log('-->', this.i18n)
        // }, 5000)
    }

    public fetch(url): Observable<any> {
        return this.http.get(this.getBaseUrl() + url)
            .pipe(
                delay(100),
                map((data: any) => (data.data || data)),
                catchError(this.handleError)
            );
    }

    private getBaseUrl() {
        return location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/';
    }


    private handleError(error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        const errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }


    // public getObservable<T>(action: ApiAction<T>): Observable<T> {
    //     const options: HttpOption = {};
    //     if (action.param) {
    //         options.params = Object.getOwnPropertyNames(action.param)
    //             .reduce((p, key) => {
    //                 if (!ValidUtil.isNullOrUndefined(action.param[key])) {
    //                     return p.set(key, action.param[key]);
    //                 } else {
    //                     return p;
    //                 }
    //             }, new HttpParams());
    //     }
    //     if (action.headers) {
    //         options.headers = action.headers;
    //     }
    //     const observable = this.http.get<T>(action.url, options);
    //     return observable;
    // }
    // https://www.techiediaries.com/angular-http-client/
    // https://www.techiediaries.com/angular-http-client/
    // public get<T>(url: string, success:(data: T|any) => void, param = {}) {


    public errorHandler(error: HttpErrorResponse | Error, title = 'Request'): void {
        if (error instanceof HttpErrorResponse && 200 === error.status && error.url) {
            location.href = error.url;
            return;
        }
        this.alertService.progressSpinnerAllOut();
        this.alertService.dangerAlertHttpErrorResponse(title, error);
    }

    public toGetDeleteHttpOption(apiHttpOptions: ApiHttpOption): HttpOption {
        const options: HttpOption = {};
        if (apiHttpOptions.params) {
            options.params = Object.getOwnPropertyNames(apiHttpOptions.params)
                .reduce((p, key) => {
                    if (!ValidUtil.isNullOrUndefined(apiHttpOptions.params[key])) {
                        return p.set(key, apiHttpOptions.params[key]);
                    } else {
                        return p;
                    }
                }, new HttpParams());
        }
        if (undefined === apiHttpOptions.headers) {
            apiHttpOptions.headers = new HttpHeaders();
        }
        const token = window.localStorage.getItem('token');
        if (!apiHttpOptions.headers.get('Authorization') && token) {
            apiHttpOptions.headers = apiHttpOptions.headers.append('Authorization', token);
        }
        options.headers = apiHttpOptions.headers;
        return options;
    }

    private toPostPutHttpOption(url: string, apiHttpOptions: ApiHttpOption): {url: string, params: any, httpOptions: HttpOption}{
        const options: HttpOption = {};
        apiHttpOptions.params = apiHttpOptions.params || {};
        if (apiHttpOptions.params instanceof FormData) {
            // url += '?_csrf=' + this.getCSRF();
        } else {
            apiHttpOptions.params._csrf = this.getCSRF();
        }
        if (undefined === apiHttpOptions.headers) {
            apiHttpOptions.headers = new HttpHeaders();
        }
        const token = window.localStorage.getItem('token');
        if (!apiHttpOptions.headers.get('Authorization') && token) {
            apiHttpOptions.headers = apiHttpOptions.headers.append('Authorization', token);
        }
        options.headers = apiHttpOptions.headers;
        return {url, params: apiHttpOptions.params, httpOptions: options};
    }

    public get<T>(url: string, options: ApiHttpOption = {}): Observable<T> {
        let pro;
        const httpObservable = this.http.get<T>(url, this.toGetDeleteHttpOption(options));
        const observable = of(true)
            .pipe(
                map(it => {
                    return pro = this.alertService.progressSpinnerOpen();
                }),
                mergeMap(it => {
                    return httpObservable;
                }),
                map(it => {
                    try{pro.out(); }catch (e) {}
                    return it;
                }),
                finalize(() => {
                    try{pro.out(); }catch (e) {}
                }),
            );
        return observable;
    }

    public delete<T>(url: string, options: ApiHttpOption = {}): Observable<T> {
        let pro;
        const httpObservable = this.http.delete<T>(url, this.toGetDeleteHttpOption(options));
        const observable = of(true)
            .pipe(
                map(it => pro = this.alertService.progressSpinnerOpen()),
                mergeMap(it => httpObservable),
                finalize(() => pro.out())
            );
        return observable;
    }

    public post<T>(url: string, options: ApiHttpOption = {}): Observable<T> {
        let pro;
        const optionsSet = this.toPostPutHttpOption(url, options);
        const httpObservable = this.http.post<T>(optionsSet.url, optionsSet.params, optionsSet.httpOptions);
        const observable = of(true)
            .pipe(
                map(it => pro = this.alertService.progressSpinnerOpen()),
                mergeMap(it => httpObservable),
                finalize(() => pro.out())
            );
        return observable;
    }

    public put<T>(url: string, options: ApiHttpOption = {}): Observable<T> {
        let pro;
        const optionsSet = this.toPostPutHttpOption(url, options);
        const httpObservable = this.http.put<T>(optionsSet.url, optionsSet.params, optionsSet.httpOptions);
        const observable = of(true)
            .pipe(
                map(it => pro = this.alertService.progressSpinnerOpen()),
                mergeMap(it => httpObservable),
                finalize(() => pro.out())
            );
        return observable;
    }

    public httpClientHandleSuccess(title: string, data: any = {}) {
        const code = data.code || 'R00000';
        const message = (data.message || MsgCode.R00000);
        this.alertService.successAlert(title + '(' + code + ')', message);

    }

    public httpClientHandleError(title: string, error: any = {}) {
        const code = error.code || 'E99999';
        const message = (error.message || MsgCode.E99999);
        this.alertService.dangerAlert(title + '(' + code + ')', message);
    }

    public getCSRF(): string {
        // return document.querySelector('meta[name="_csrf"]').getAttribute('content');
        return this.cookieService.get('XSRF-TOKEN');
    }
}

export type ApiHttpOption = {
    params?: any;
    headers?: HttpHeaders;
};


