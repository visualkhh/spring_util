import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from '@app/services/ui/alert.service';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {JsonApiService} from '@app/services/json-api.service';

import {Router} from '@angular/router';
import {asyncScheduler, Observable, of, EMPTY, zip, pipe, combineLatest, concat, merge} from 'rxjs';
import {finalize, mergeMap, map, tap, subscribeOn, startWith, concatAll} from 'rxjs/operators';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    @ViewChild(HomeListComponent) modal: HomeListComponent;


    constructor(private http: HttpClient, private alertService: AlertService, public router: Router, private api: JsonApiService) {
    }

    ngOnInit() {}
}
