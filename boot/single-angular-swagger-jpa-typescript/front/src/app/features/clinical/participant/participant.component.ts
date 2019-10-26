import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ParticipantModalComponent} from "./modal/participant-modal.component";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, User, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";

declare let $: any;
import * as moment from 'moment'

import {Moment} from "smartadmin-plugins/bower_components/moment/moment";
interface WhereType {
    ptcpStDt?: string,
    ptcpEndDt?: string,
    ptcpCd?: string,
    beforeWeekConformity?: string,
    ptcpGrpCd?: string,
    nm?: string,
    cpon?: string,
    lstSerialNo?: string,
}

@Component({
    selector: 'app-participant',
    templateUrl: './participant.component.html',
    styleUrls: ['./participant.component.css'],
    providers: [CmsCommonService]
})
export class ParticipantComponent implements OnInit {

    @ViewChild(ParticipantModalComponent) modal: ParticipantModalComponent;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    public i18n: I18nService;
    public where: WhereType = {};

    constructor(private route: ActivatedRoute, private momentPipe: MomentPipe, private validationService: ValidationService, public router: Router, private jsonApiService: JsonApiService, public userService: UserService) {
        this.i18n = I18nService.getInstance();
        this.i18n. subscribe(() => {
            if(this.table)
            this.table.draw()
        });
    }

    ngOnInit() {
    }

    options = {
        serverSide: true,
        paging: true,
        ordering: false,
        // language: {
        //     emptyTable: I18nService.getInstance().getTranslation("No data available in table")
        // },
        // scrollY:        "300px",
        // scrollX:        true,
        // scrollCollapse: true,
        // fixedColumns:   {
        //     leftColumns: 1,
        //     rightColumns: 1
        // },
        lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
        // deferLoading: 0, //auto ajax start disable
        ajax: (data, callback, setting) => {
            const pageWhere = {"size": data.length, "page": data.start / data.length, "draw": data.draw} as DataTablePageRequest;
            let where = Object.assign({}, this.where);
            if (where.ptcpStDt) {
                // where.ptcpStDt = moment(where.ptcpStDt, 'YYYY.MM.DD').format('YYYY-MM-DDTHH:mm:ss.SSSZ');
                where.ptcpStDt = moment(where.ptcpStDt).toISOString();
            }
            if (where.ptcpEndDt) {
                // where.ptcpEndDt = moment(where.ptcpEndDt, 'YYYY.MM.DD').format('YYYY-MM-DDTHH:mm:ss.SSSZ'); //.toISOString();
                where.ptcpEndDt = moment(where.ptcpEndDt).add(1, 'day').toISOString();
            }
            where = Object.assign(where, pageWhere);
            const action =new ApiAction<DataTablePageResponse<User>>(this.router.url);
            action.param = where;
            action.success = (data: DataTablePageResponse<User>) => {
                if (data.data && data.data.length) {
                    data.data.forEach(it => {
                        it.ptcpStDt = moment(it.ptcpStDt).toDate();
                        it.ptcpEndDt = moment(it.ptcpEndDt).toDate();
                        it.birdt = moment(it.birdt).toDate();
                    });
                }
                callback(data);
            }
            this.jsonApiService.get(action);
        },
        columns: [
            {data: 'userSeq', className: 'text-center col-md-1', defaultContent: ""},
            {data: 'nm', className: 'text-center ', defaultContent: ""},
            {data: 'cpon', className: 'text-center ', defaultContent: ""},
            {data: 'genCd', className: 'text-center ', defaultContent: "", render: (data, display, row) => this.i18n.getTranslation(data)},
            {data: 'birdt', className: 'text-center ', defaultContent: "",
                render: (data, display, row) => {
                    return Number(moment().format('YYYY')) - Number(moment(data).format('YYYY'));
                }
            },
            {data: 'lstSerialNo', className: 'text-center ', defaultContent: ""},
            {data: 'ptcpGrpCd', className: 'text-center ', defaultContent: "", render: (data, display, row) => this.i18n.getTranslation(data)},
            {data: 'conformityCd', className: 'text-center ', defaultContent: "",
                render: (data, display, row: User) => {
                    const m = moment(new Date());
                    m.subtract(1, 'week');
                    const monday = m.isoWeekday('Monday').format('YYYYMMDD');
                    const sunday = m.isoWeekday('Sunday').format('YYYYMMDD');
                    // console.log('전주 순응도 구하기.',row.nm, '----=> ', monday,  sunday)
                    let cd = 'CFT002';
                    for (let i = 0; row.gameSetWeekRsts && i < row.gameSetWeekRsts.length; i++) {
                        const gameSetWeekRst = row.gameSetWeekRsts[i];
                        const imonday = moment(gameSetWeekRst.startDt).isoWeekday('Monday').format('YYYYMMDD');
                        const isunday = moment(gameSetWeekRst.endDt).isoWeekday('Sunday').format('YYYYMMDD');
                        // 전주 순응도 구하기.
                        if (imonday >= monday && sunday >= isunday) {
                            cd = gameSetWeekRst.conformityCd;
                            break;
                        }
                    }

                    if (cd == 'CFT001')
                        var str = 'label-success';
                    else
                        var str = 'label-danger';
                    return '<span action="conformityCd" class="center-block padding-5 label ' + str + '">' + I18nService.getInstance().getTranslation(cd) + '</span>';
                }
            },
            {data: 'ParticipantDate', className: 'text-center ', defaultContent: "",
                render: (data, display, row: User) => {
                    return moment(row.ptcpStDt).format('YYYY.MM.DD') + '~' + moment(row.ptcpEndDt).format('YYYY.MM.DD');
                }
            },
            {data: 'ptcpCd', className: 'text-center', defaultContent: "", render: (data) => this.i18n.getTranslation(data)},
            {data: 'useCd', className: 'text-center', defaultContent: "", render: (data) => this.i18n.getTranslation(data)},
        ],
        drawCallback: function(settings) {
            console.log('draw call back-->',settings);
            //do whatever
        },
        rowCallback: (row: Node, data: User, index: number) => {
            $('td', row).unbind('click');
            $('td', row).bind('click', (e) => {
                let actionStr: string = $(e.target).attr("action");
                // this.modal.show(Object.assign({}, data) as User);
                //https://www.concretepage.com/angular-2/angular-2-4-child-routes-and-relative-navigation-example#navigation
                this.router.navigate([data.userSeq], { relativeTo: this.route });
            });
            return row;
        }
    };

//moment : https://momentjs.com/docs/
    private showRegDialog() {
        this.modal.show();
    }

    //
    // private noticeDelete() {
    //     let deleteArr: Array<string> = new Array<string>();
    //     $.each($('#bbsTable tbody input[type="checkbox"]:checked'), (key, value) => {
    //         deleteArr.push($(value).val());
    //     });
    //     if (deleteArr.length > 0) {
    //         /*this.http.post(`${this.router.url}/delete`, {"deleteParam": deleteArr})
    //             .catch(this.bbsModal.handleError)
    //             .subscribe((data: any) => {
    //                 $("#bbsTable table").DataTable().ajax.reload();
    //             });*/
    //     }
    // }

    public search() {
        this.table.draw();
        // console.log(this.where.search);
    }

    add() {
    }

    modalComplete(wow: any) {
        this.where = {};
        this.table.draw();
    }

    ptcpStDtChange(data: any|Date|Event) {
       if(data instanceof Event) {
           if(this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
           } else {
               data.target['value'] = "";
               data = null;
           }
       }
        this.where.ptcpStDt = data;
    }
    ptcpEndDtChange(data: any|Date|Event) {
        if(data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = "";
                data = null;
            }
        }
        this.where.ptcpEndDt = data;
    }

}
