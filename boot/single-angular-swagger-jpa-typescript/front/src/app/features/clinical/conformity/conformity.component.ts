import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-notice',
    templateUrl: './conformity.component.html',
    styleUrls: ['./conformity.component.css'],
    providers: [CmsCommonService]
})

export class ConformityComponent implements OnInit {

    public tabState = 0;

    constructor(private route: ActivatedRoute, private momentPipe: MomentPipe, private http: HttpClient, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private i18: I18nService) {
    }

    ngOnInit() {
    }



    public search() {
    }

    add() {
    }

}
