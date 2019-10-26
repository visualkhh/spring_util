import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, ComCd, DataTablePageRequest, DataTablePageResponse, GameRst, GameSetDayRst, GameSetWeekRst, Gift, User, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";
import {Moment} from "smartadmin-plugins/bower_components/moment/moment";
import {timer} from "rxjs";
import {ParticipantModalComponent} from "@app/features/clinical/participant/modal/participant-modal.component";
import {ConformityDetailsModalComponent} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.component";
import {GiftModalComponent} from "@app/features/clinical/conformity/details/gift/modal/gift-modal.component";

declare let $: any;
declare let moment: any;

interface WhereType {
    startDt?: Date,
    endDt?: Date,
    nm?: string,
    cpon?: string,
    comCd?: ComCd,
}

@Component({
    selector: 'app-conformity-gift',
    templateUrl: './conformity-gift.component.html',
    styleUrls: ['./conformity-gift.component.css'],
    providers: [CmsCommonService]
})


export class ConformityGiftComponent implements OnInit {
    @ViewChild(GiftModalComponent) modal: GiftModalComponent;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    private i18n: I18nService;
    public where: WhereType = {};


    constructor(private route: ActivatedRoute, private cmsCommonService: CmsCommonService, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private validationService: ValidationService) {
        this.i18n = I18nService.getInstance();
        this.i18n. subscribe(() => {
            if(this.table)
                this.table.draw()
        });
    }

    ngOnInit() {
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
        lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
        buttons: [
            {
                extend: 'excelHtml5',
                text: I18nService.getInstance().getTranslation('Excel'),
                title: 'conformity-gift',
            }
        ],
        ajax: (data, callback, setting) => {
            let pageWhere = {"size": data.length, "page": data.start / data.length, "draw": data.draw} as DataTablePageRequest;
            let where = Object.assign({}, this.where);
            if(this.where.startDt) {
                where['startDt'] = moment(this.where.startDt).toISOString();
            }
            if(this.where.endDt) {
                where['endDt'] = moment(this.where.endDt).toISOString();
            }
            where = Object.assign(where, pageWhere);

            const action = new ApiAction<DataTablePageResponse<GameSetWeekRst>>(this.router.url+"/gifts");
            action.param = where;
            action.success = (data: DataTablePageResponse<Gift>) => {
                if (data.data && data.data.length) {
                    data.data.forEach(it => {
                        it.regDt = moment(it.regDt).toDate();
                        it.reqDt = moment(it.reqDt).toDate();
                    });
                }
                callback(data);
            }
            this.jsonApiService.get(action);
        },
        columns: [
            {data: 'giftSeq', className: 'text-center col-md-1', defaultContent: ""},
            {data: 'regDt', className: 'text-center ', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return moment(data).format('YYYY.MM.DD HH:mm:ss');
                }
            },
            {data: 'nm', className: 'text-center ', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return row.user.nm;
                }
            },
            {data: 'cpon', className: 'text-center ', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return row.user.cpon;
                }
            },
            {data: 'genCd', className: 'text-center ', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return I18nService.getInstance().getTranslation(row.user.genCd);
                }
            },
            {data: 'age', className: 'text-center', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return Number(moment().format('YYYY')) - Number(moment(row.user.birdt).format('YYYY'));
                }
            },
            {data: 'ptcpGrpCd', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return I18nService.getInstance().getTranslation(row.user.ptcpGrpCd);
                }
            },
            {data: 'giftCd', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return I18nService.getInstance().getTranslation(row.giftCd);
                }
            },
            {data: 'gameCd', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: Gift) => {
                    return I18nService.getInstance().getTranslation(row.gameCd);
                }
            },
            {data: 'comCd', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: Gift) => {
                    if (data == 'COM001')
                        var str = 'label-success';
                    else
                        var str = 'label-danger';
                    return '<span action="conformityCd" class="btn center-block padding-5 label ' + str + '">' + I18nService.getInstance().getTranslation(data) + '</span>';
                }
            },
        ],
        rowCallback: (row: Node, data: Gift, index: number) => {
            $('td', row).unbind('click');
            $('td', row).bind('click', (e) => {
                let actionStr: string = e.target.getAttribute('action');
                this.modal.show(data);
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
        this.where.startDt = data; //this.cmsCommonService.getMondayOfWeek(data);
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
        let m = moment(data) as Moment;
        this.where.endDt = m.add(1, 'day').toDate(); //this.cmsCommonService.getSundayOfWeek(data);
    }

    modalComplete($event: any) {
        this.selectStartDate("");
        this.selectEndDate("");
        this.table.draw();
    }
}
