import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";

import {ApiAction, JsonApiService} from "@app/core/services/json-api.service";
declare let $: any;
import {Brd, DataTablePageResponse} from "@app/model/commomModels";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

    private notice_url: string = "/brd/notice";

    constructor(private http: HttpClient, private jsonApiService:JsonApiService, private router: Router) {
    }

    ngOnInit() {
    }

    noticeOptions = {
        serverSide: true,
        paging: true,
        ordering: false,
        ajax: (data, callback, setting) => {
            let form = {};
            /*$.each($("form").serializeArray(), function (i, field) {
                form[field.name] = field.value || "";
            });*/
            form["search"] = $("[name=searchWord]").val();
            /*"size": data.length*/
            let info = {"start": data.start, "size": 3, "page": data.start / data.length, "draw": data.draw};
            $.extend(form, info);
            const action = new ApiAction(this.notice_url);
            action.param = form;
            action.success = (data: DataTablePageResponse<Brd>) => {
                // this.jsonApiService.httpClientHandleSuccess(data);
                callback(data);
            };
            this.jsonApiService.get(action);
        },
        columns: [
            {data: 'titl', className: 'text-center ', defaultContent: ""}
        ],
        rowCallback: (row: Node, data: any[] | Object, index: number) => {
            console.log("tk_console_row_click =====> ", data);
        }
    };
}
