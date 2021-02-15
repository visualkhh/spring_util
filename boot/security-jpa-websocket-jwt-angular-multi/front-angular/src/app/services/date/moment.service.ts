import {Injectable, OnInit} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {JsonApiService} from '@app/services/json-api.service';
import {map} from 'rxjs/internal/operators/map';
import {throwError} from 'rxjs/internal/observable/throwError';
import {catchError} from 'rxjs/internal/operators/catchError';
import {of} from 'rxjs/internal/observable/of';
import {filter} from 'rxjs/internal/operators/filter';
import {finalize} from 'rxjs/internal/operators/finalize';
import {AlertService} from '@app/services/ui/alert.service';
import moment from 'moment';
declare let $: any;

@Injectable()
export class MomentService  {

    constructor(private http: HttpClient, private alertService: AlertService, private api: JsonApiService, private httpClient: HttpClient, private router: Router) {
        // console.log(moment('20140304', 'YYYY.MM.DD').format('YYYY.MM.DD'));
        // import('../../../libs/bootstrap-viewtree/bootstrap-treeview.min').then(_ => {
        //     // this.render();
        // });
        // import('moment/dist/moment.min.js').then(_ => {
        //
        // });
        // import('script-loader!')
        // import('moment/dist/moment.js').then(_ => {
        // //
        // })
    }
    // moment(... param: any[]) {
    //     return moment.apply(null, param);
    // }

    moment = moment;

    // public momentToISOString(data: any) {
    //     this.moment(data).toISOString();
    // }

}
