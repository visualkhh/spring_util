import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, GameSetDayRst, GameSetWeekRst, User, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";
import {Moment} from "smartadmin-plugins/bower_components/moment/moment";
import {timer} from "rxjs";
import {ParticipantModalComponent} from "@app/features/clinical/participant/modal/participant-modal.component";
import {ConformityDetailsModalComponent} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.component";

declare let $: any;
declare let moment: any;
// interface Dictionary<T> {
//     [key: string]: T;
// }
// interface Where {
//     fromDate: Date;
//     toDate: Date;
// }

@Component({
    selector: 'app-participant-conformity',
    templateUrl: './participant-conformity.component.html',
    styleUrls: ['./participant-conformity.component.css'],
    providers: [CmsCommonService]
})


export class ParticipantConformityComponent implements OnInit {
    @ViewChild(ConformityDetailsModalComponent) modal: ConformityDetailsModalComponent;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    @Input() user: User;
    private startDt: Date;
    private endDt: Date;
    private i18n: I18nService;

    constructor(private route: ActivatedRoute, private cmsCommonService: CmsCommonService, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private validationService: ValidationService) {
        this.i18n = I18nService.getInstance();
        this.i18n. subscribe(() => {
            if(this.table)
                this.table.draw()
        });
    }

    ngOnInit() {
        console.log(this.user);
        // this.selectStartDate(this.user.ptcpStDt);
        // this.selectEndDate(this.user.ptcpEndDt);
        // this.selectDate(new Date());
        // this.where = {fromDate: this.cmsCommonService.getMondayOfWeek(), toDate: this.cmsCommonService.getSundayOfWeek()};
        // this.betweenDate = this.cmsCommonService.betweenDates(this.where.fromDate, this.where.toDate, 'days', 1);
        // timer(5000).subscribe(it => this.table.render());
    }

    options = {
        // autoRender: false,
        serverSide: true,
        paging: true,
        ordering: false,
        buttons: [
            {
                extend: 'excelHtml5',
                text: I18nService.getInstance().getTranslation('Excel'),
                title: 'clinical-participant-participant',
            }
        ],
        lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
        ajax: (data, callback, setting) => {
            let where = {"size": data.length, "page": data.start / data.length, "draw": data.draw} as DataTablePageRequest;
            if(this.startDt) {
                where['startDt'] = moment(this.startDt).toISOString();
            }
            if(this.startDt) {
                where['endDt'] = moment(this.endDt).toISOString();
            }
            const action = new ApiAction<DataTablePageResponse<GameSetWeekRst>>(this.router.url+"/conformity");
            action.param = where;
            action.success = (data: DataTablePageResponse<GameSetWeekRst>) => {
                if (data.data && data.data.length) {
                    data.data.forEach(it => {
                        it.startDt = moment(it.startDt).toDate();
                        it.endDt = moment(it.endDt).toDate();
                    });
                }
                callback(data);
            }
            this.jsonApiService.get(action);
        },
        columns: [
            // {data: 'brdSeq', className: 'text-center col-md-1', defaultContent: ""},
            {data: 'dt', className: 'text-center ', defaultContent: "",
                render: (data, display, row: GameSetWeekRst) => {
                    return moment(row.startDt).format('YYYY.MM.DD') + '~' + moment(row.endDt).format('YYYY.MM.DD');
                }
            },
            {data: 'monday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'tuesday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'wednesday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'thursday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'friday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'saturday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'sunday', className: 'text-center col-md-1', defaultContent: "", render: this.dayRender},
            {data: 'conformityCd', className: 'text-center col-md-1', defaultContent: "", visible: true,
                render: (data, display, row) => {
                    if (data == 'CFT001')
                        var str = 'label-success';
                    else
                        var str = 'label-danger';
                    return '<span action="conformityCd" class="btn center-block padding-5 label ' + str + '">' + I18nService.getInstance().getTranslation(data) + '</span>';
                }
            },
            {data: 'conformityCdAlert', className: 'text-center col-md-2', defaultContent: "",
                render: (date) => {
                    return " <span action='conformityCdAlert' class='btn center-block padding-5 label label-success'>"+I18nService.getInstance().getTranslation('Send')+"</span>";
                    // return "<span class='center-block padding-5 label-info'> "+I18nService.getInstance().getTranslation('Send')+" </span>"
                }
            }
        ],
        rowCallback: (row: Node, data: GameSetWeekRst, index: number) => {
            $('td', row).unbind('click');
            $('td', row).bind('click', (e) => {
                let actionStr: string = e.target.getAttribute('action');
                if ('conformityCd' === actionStr) {
                    this.modal.show(data.userSeq, data.startDt, data.endDt);
                } else if ('conformityCdAlert' === actionStr) {


                    const action = new ApiAction<void>(this.router.url+"/conformity/emails");
                    action.param = data;
                    // action.success = (data: void) => {
                    // }
                    this.jsonApiService.post(action);





                }
            });
            return row;
        }
    };


    public search() {
        this.table.draw();
        // console.log(this.where.search);
    }
    dayRender(data, display, row) {
        let iconHtml = "";
        if(data === 1) {
            // iconHtml = "<span class='badge bg-color-grayDark txt-color-white'>"+data+"</span>";
            iconHtml = "<span style='color: #898989; font-size: 4px;'>"+data+"</span>";
        } else if (data >= 2) {
            iconHtml = "<span class='badge bg-color-greenLight txt-color-white'>"+data+"</span>";
            // iconHtml = "<i class='fa fa-circle'></i>"
        }
        // return iconHtml+"("+data+")";

        return iconHtml;
    }


    selectStartDate(data: Date | any | Event) {
        if (data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = '';
                data = null;
            }
        }
        this.startDt = this.cmsCommonService.getMondayOfWeek(data);
    }

    selectEndDate(data: Date | any | Event) {
        if (data instanceof Event) {
            if (this.validationService.isDateFormat(data.target['value'])) {
                data = moment(data.target['value'], 'YYYY.MM.DD').toDate()
            } else {
                data.target['value'] = '';
                data = null;
            }
        }
        this.endDt = this.cmsCommonService.getSundayOfWeek(data);
    }
}
