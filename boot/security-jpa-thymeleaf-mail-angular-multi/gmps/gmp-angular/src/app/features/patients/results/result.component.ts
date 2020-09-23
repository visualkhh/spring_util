import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {CodeService} from '@web-core/app/services/code/code.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {Title} from '@angular/platform-browser';
import {MomentPipe} from '@web-core/app/pipes/date/moment.pipe';

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-patient',
    templateUrl: './result.component.html',
    styleUrls: ['./result.component.css'],
    providers: []
})
export class ResultComponent implements OnInit, OnDestroy {


    constructor(private momentPipe: MomentPipe, private http: HttpClient, private router: Router, private codes: CodeService,
                private i18n: I18nService, private api: JsonApiService, private title: Title) {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
    }

}
