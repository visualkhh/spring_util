import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {
    BrdCateCd,
    DataTablePageRequest,
    DataTablePageResponse,
    GameRst,
    GameSetDayRst,
    GameSetWeekRst,
    GenderCode,
    Gift,
    ParentCd,
    PtcpCd,
    PtcpGrpCd, UseCd,
    User,
    UserDetails
} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";
import {Moment} from "smartadmin-plugins/bower_components/moment/moment";
import {Observable, timer} from "rxjs";
import {of} from "rxjs";
import {ParticipantModalComponent} from "@app/features/clinical/participant/modal/participant-modal.component";
import {ConformityDetailsModalComponent} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.component";
import {a} from "@angular/core/src/render3";


declare let $: any;
declare let moment: any;
// require( 'datatables.net-fixedcolumns-bs' )();
// require( 'datatables.net-fixedheader-bs' )();
interface WhereType {
    ptcpStDt?: Date,
    ptcpEndDt?: Date,
    nm?: string,
    ptcpGrpCd?: PtcpGrpCd,
    ptcpCd?: PtcpCd,
    lstSerialNo?: string,
    genCd?: GenderCode,
    cpon?: string,
}

class UserCalc implements User{
    birdt: Date;
    cont: string;
    cpon: string;
    gadCpon: string;
    gadEmail: string;
    gadNm: string;
    gadRltspCd: ParentCd;
    gameSetWeekRsts: GameSetWeekRst[];
    genCd: GenderCode;
    lstLginDt: Date;
    lstSerialNo: string;
    nm: string;
    ptcpCd: PtcpCd;
    ptcpEndDt: Date;
    ptcpGrpCd: PtcpGrpCd;
    ptcpStDt: Date;
    regDt: Date;
    updDt: Date;
    useCd: UseCd;
    userSeq: number;

    // ----------
    private currentWeek: number;
    private currentConformityPercent: number;
    private days: Map<number, number>;
    public getDay(time: number) {
        if (!this.days) {
            this.days = new Map<number, number>();
            for (let i = 0; this.gameSetWeekRsts && i < this.gameSetWeekRsts.length; i++) {
                let rst = this.gameSetWeekRsts[i];
                this.days.set(rst.startDt.getTime(), rst.monday);
                let m = moment(rst.startDt);
                this.days.set(m.add(1, 'day').toDate().getTime(), rst.tuesday);
                this.days.set(m.add(2, 'day').toDate().getTime(), rst.wednesday);
                this.days.set(m.add(4, 'day').toDate().getTime(), rst.thursday);
                this.days.set(m.add(5, 'day').toDate().getTime(), rst.saturday);
                this.days.set(m.add(6, 'day').toDate().getTime(), rst.sunday);
            }
        }
        return this.days.get(time);
    }

    public getCurrentWeek(date = new Date()) {
        if (undefined === this.currentWeek) {
            let stDate = new Date(this.ptcpStDt.getTime());
            let w = 0;
            while (stDate.getTime() <= date.getTime()) {
                stDate =  moment(stDate).add(1, 'week').toDate();
                w++;
            }
            this.currentWeek = w;
        }
        return this.currentWeek;
    }

    public getCurrentConformityPercent(date = new Date()) {
        if (undefined === this.currentConformityPercent) {
            const target = this.gameSetWeekRsts.filter(it => {
                return this.ptcpStDt.getTime() <= it.endDt.getTime() && date.getTime() >= it.endDt.getTime() && it.conformityCd === 'CFT001'
            });
            // const target = this.gameSetWeekRsts.filter(it => {
            //     this.ptcpStDt.getTime() <= it.endDt.getTime() && date.getTime() >= it.endDt.getTime() && it.conformityCd === 'CFT001'
            // });
            let currentWeek = this.getCurrentWeek();
            if (target.length > 0  && currentWeek > 0) {
                this.currentConformityPercent = Number(Number((target.length / currentWeek) * 100).toFixed(1));
            } else {
                this.currentConformityPercent = 0
            }
        }
        return this.currentConformityPercent;

    }
}
class UserData {
    // public users: UserCalc[];
    public ptcpStDtMonday: Date;
    public ptcpStDt: Date;
    public ptcpEndDt: Date;
    public ptcpEndDtSunday: Date;
    public ptcpStDtMondayToPtcpEndDtSundayFullDay: Date[];
    public ptcpStDtMondayToPtcpEndDtSundayFullLastMonthDay: Date[];
    constructor(public users: UserCalc[]) {
        this.init();
    }
    init() {
        // this.users = new Array<UserCalc>();
        // users.forEach(it => this.users.push(Object.assign(new UserCalc(), it)));

        this.ptcpStDt = this.getPtcpStDt();
        this.ptcpEndDt = this.getPtcpEndDt();
        this.ptcpStDtMonday = this.getPtcpStDtMonday();
        this.ptcpEndDtSunday = this.getPtcpEndDtSunday();
        this.ptcpStDtMondayToPtcpEndDtSundayFullDay = this.getPtcpStDtMondayToPtcpEndDtSundayFullDay()
        this.ptcpStDtMondayToPtcpEndDtSundayFullLastMonthDay = this.getPtcpStDtMondayToPtcpEndDtSundayFullLastMonthDay()
    }
    getPtcpStDtMonday() {
        return moment(this.ptcpStDt).isoWeekday('Monday').toDate();
    }
    getPtcpEndDtSunday() {
        return moment(this.ptcpEndDt).isoWeekday('Sunday').toDate();
    }
    getPtcpStDt() {
        // Observable.from(this.users).min((a, b) => Math.min(a.ptcpStDt.getTime(), b.ptcpStDt.getTime())).subscribe(it => console.log(it));
        // of(this.users).min()
        // return null;
        var min = this.users.reduce((previous, current, i, a) => {
            return previous.ptcpStDt.getTime() > current.ptcpStDt.getTime() ? current : previous;
        });
        return min.ptcpStDt;
    }
    getPtcpEndDt() {
        //최대값
        var max = this.users.reduce((previous, current, i, a) => {
            return previous.ptcpEndDt.getTime() > current.ptcpEndDt.getTime() ? previous : current;
        });
        return max.ptcpEndDt;
        // Observable.from(this.users).min((a, b) => Math.min(a.ptcpStDt.getTime(), b.ptcpStDt.getTime())).subscribe(it => console.log(it));
    }

    getPtcpStDtMondayToPtcpEndDtSundayFullDay(user?: User) {
        const r = new Array<Date>();
        let startDt = this.ptcpStDtMonday;
        while (startDt.getTime() <= this.ptcpEndDtSunday.getTime()) {
            // add(6 - moment(date).day() + 1, 'day').toDate();
            r.push(startDt);
            startDt = moment(startDt).add(1, 'day').toDate();
        }
        return r;
    }
    getPtcpStDtMondayToPtcpEndDtSundayFullLastMonthDay() {
        const r = new Array<Date>();
        let startDt = moment(this.ptcpStDtMonday).startOf('month').toDate();
        while (startDt.getTime() <= this.ptcpEndDtSunday.getTime()) {
            // console.log(moment(startDt).endOf('month').format('YYYY.MM.DD'));
            r.push(moment(startDt).endOf('month').toDate());
            startDt = moment(startDt).add(1, 'month').toDate();
        }
        return r;
    }

}

@Component({
    selector: 'app-conformity-conform',
    templateUrl: './conformity-conform.component.html',
    styleUrls: ['./conformity-conform.component.css'],
    providers: [CmsCommonService]
})


export class ConformityConformComponent implements OnInit {
    @ViewChild(ConformityDetailsModalComponent) modal: ConformityDetailsModalComponent;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    private i18n: I18nService;
    public where: WhereType = {};
    public data: UserData;
    public isNewHead = false;
    private options: any;
    private dataTable: any;
    private progress: any;


    constructor(private route: ActivatedRoute, private cmsCommonService: CmsCommonService, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private validationService: ValidationService,  private notificationService: NotificationService) {
        this.i18n = I18nService.getInstance();
        // this.i18n. subscribe(() => {
        //     if(this.table)
        //         this.table.draw()
        // });
        // timer(4000).subscribe(it => {
        //     for (let i = 0; i < 10; i++) {
        //         this.data.push({name:'11 - '+i});
        //     }
        // })
        // timer(5000).subscribe(it => {
        //     this.table.destroy();
        // })
        this.search();
    }

    ngOnInit() {
        // this.selectStartDate(this.user.ptcpStDt);
        // this.selectEndDate(this.user.ptcpEndDt);
        // this.selectDate(new Date());
        // this.where = {fromDate: this.cmsCommonService.getMondayOfWeek(), toDate: this.cmsCommonService.getSundayOfWeek()};
        // this.betweenDate = this.cmsCommonService.betweenDates(this.where.fromDate, this.where.toDate, 'days', 1);
        // timer(5000).subscribe(it => this.table.render());
        //
        // var table = $('#example').DataTable( {
        //     scrollY:        "300px",
        //     scrollX:        true,
        //     scrollCollapse: true,
        //     ordering: false,
        //     paging: true,
        //     lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
        //     fixedColumns:   {
        //         leftColumns: 2,
        //         // rightColumns: 1
        //     },
        //     buttons: [
        //         {
        //             extend: 'excelHtml5',
        //             text: I18nService.getInstance().getTranslation('Excel'),
        //             title: 'conformity-gift',
        //         }
        //     ],
        //     /*
        //      dom:
        //         "<'dt-toolbar'<'col-xs-12 col-sm-6'><'col-sm-6 col-xs-12 hidden-xs text-right'" +
        //         toolbar + "" +
        //         ">r>" +
        //         "t" +
        //         "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
        //      */
        //     dom:
        //         "<'dt-toolbar'<'col-xs-12 col-sm-6 text-right' ><'col-sm-6 col-xs-12 hidden-xs text-right' Bl>r> t <'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>" ,
        //         // "t" +
        //         // "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
        //     oLanguage: {
        //         sSearch:
        //             "<span class='input-group-addon'><i class='glyphicon glyphicon-search'></i></span> ",
        //         sLengthMenu: "_MENU_"
        //     },
        //     // autoWidth: false,
        //     // retrieve: true,
        //     // responsive: true,
        // } );
        // $('#example').on( 'click', function () {
        //     table.destroy();
        // });
    }




    public search() {
        // this.table.draw();
        // console.log(this.where.search);
        if (this.dataTable) {
            try{
                this.dataTable.destroy();
            }catch (e) {
            }
            $("#targetDataTable").empty();
        }
        this.data = undefined;
        let where = Object.assign({}, this.where);
        if(this.where.ptcpStDt) {
            where['ptcpStDt'] = moment(this.where.ptcpStDt).toISOString();
        }
        if(this.where.ptcpEndDt) {
            where['ptcpEndDt'] = moment(this.where.ptcpEndDt).toISOString();
        }

        const action = new ApiAction<Array<GameSetWeekRst>>(this.router.url+"/conforms");
        action.param = where;
        action.success = (data: Array<User>) => {
            if (data && data.length) {
                const users = new Array<UserCalc>();
                // users.forEach(it => this.users.push(Object.assign(new UserCalc(), it)));
                data.forEach(it => {
                    it.ptcpStDt = moment(it.ptcpStDt).toDate();
                    it.ptcpEndDt = moment(it.ptcpEndDt).toDate();
                    it.regDt = moment(it.regDt).toDate();
                    it.gameSetWeekRsts.forEach(sit => {
                        sit.startDt =  moment(sit.startDt).toDate();
                        sit.endDt =  moment(sit.endDt).toDate();
                    })
                    users.push(Object.assign(new UserCalc(), it))
                });



                this.isNewHead = true;
                this.data = new UserData(users) ;
                // this.tableReSettingAndRender(this.data)
                this.progress = this.notificationService.progressSpinnerOpen();
                timer(1).subscribe(it=>this.tableReSettingAndRender(this.data));
            }
        }
        this.jsonApiService.get(action);
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
        this.where.ptcpStDt = this.cmsCommonService.getMondayOfWeek(data); //data
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
        this.where.ptcpEndDt = this.cmsCommonService.getSundayOfWeek(data); //m.add(1, 'day').toDate(); //
    }

    tableDestoryCompleted($event: any) {
        // this.data.push({name:'11 - '});
        // this.options['targetContainerSelctor'] = '#newHeader';

        // timer(2000).subscribe(it => {
        //     this.table.render();
        // });
    }

    newHeadComplet() {
        if (this.isNewHead) {
            console.log('newHeadComplet');
           this.table.render();
            this.isNewHead = false;
        }
    }

    private tableReSettingAndRender(userData: UserData) {
        var fixSize = 7;

        let columns = [
            {data: 'userSeq', className: 'text-center', defaultContent: ""},
            {data: 'nm', className: 'text-center', defaultContent: ""},
            {data: 'ptcpGrpCd', className: 'text-center ', defaultContent: "",
                render: (data, display, rowData: UserCalc) => {
                    return I18nService.getInstance().getTranslation(data);
                }
            },
            {data: 'ptcpCd', className: 'text-center', defaultContent: "",
                render: (data, display, rowData: UserCalc) => {
                    return I18nService.getInstance().getTranslation(data);
                }
            },
            {data: 'lstSerialNo', className: 'text-center ', defaultContent: ""},
            {data: 'week', className: 'text-center ', defaultContent: "",
                render: (data, display, rowData: UserCalc) => {
                    return rowData.getCurrentWeek();
                    // return (moment(rowData.ptcpEndDt).year()+moment(rowData.ptcpEndDt).isoWeek()) - (moment(rowData.ptcpStDt).year()+moment(rowData.ptcpStDt).isoWeek());
                    // return moment(rowData.ptcpStDt).isoWeek() + ", " +moment(rowData.ptcpEndDt).isoWeek();
                }
            },
            {data: 'conformity', className: 'text-center ', defaultContent: "",
                render: (data, display, rowData: UserCalc) => {
                    return rowData.getCurrentConformityPercent()+"%";
                }
            }
        ];

        // day columns
        userData.ptcpStDtMondayToPtcpEndDtSundayFullDay.forEach(it => {
            // console.log(moment(it).format('YYYYMMDD'))
            columns.push(
                {data: moment(it).format('YYYYMMDD'), className: 'text-center no-padding', defaultContent: "",
                    render: (data, display, rowData: UserCalc) => {
                        const comData = rowData.getDay(it.getTime());
                        let iconHtml = "";
                        if (comData === 1) {
                            iconHtml = "<span style='color: #898989; font-size: 4px;'>"+comData+"</span>";
                        } else if (comData >= 2) {
                            iconHtml = "<span class='badge bg-color-greenLight txt-color-white'>"+comData+"</span>";
                        }

                        // if (userCalc.ptcpStDt.getTime() === it.getTime()) {
                        //     iconHtml += "▶︎";
                        // } else if (userCalc.ptcpEndDt.getTime() === it.getTime()) {
                        //     iconHtml += "◀︎";
                        // } else if (userCalc.ptcpStDt.getTime()  <= it.getTime() && userCalc.ptcpEndDt.getTime() >= it.getTime()) {
                        //     iconHtml = ".";
                        // }
                        if (rowData.ptcpStDt.getTime()  <= it.getTime() && rowData.ptcpEndDt.getTime() >= it.getTime() && iconHtml.length<=0) {
                            iconHtml = ".";
                        }
                        return iconHtml;
                    }
                }
            )
        });



        this.dataTable = $('#targetDataTable').DataTable( {
            columns: columns,
            data: userData.users,
            scrollY:        "300px",
            scrollX:        true,
            scrollCollapse: true,
            ordering: false,
            paging: true,
            lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
            fixedColumns:   {
                leftColumns: fixSize,
            },
            buttons: [
                {
                    extend: 'excelHtml5',
                    text: I18nService.getInstance().getTranslation('Excel'),
                    title: 'conformity-conformity',
                    exportOptions: {
                        format: {
                            header: (data, row, column, node) => {
                                // Strip $ from salary column to make it numeric
                                console.log(data, row, column, node);
                                var i = (row - fixSize);
                                if (this.data.ptcpStDtMondayToPtcpEndDtSundayFullDay[i]) {
                                    return moment(this.data.ptcpStDtMondayToPtcpEndDtSundayFullDay[i]).format('YYYY.MM.DD');
                                } else {
                                    return data;
                                }
                            },
                            // body: function (data, row, column, node) {
                            //     // Strip $ from salary column to make it numeric
                            //     console.log(data, row, column, node);
                            //     return column === 5 ?
                            //         data.replace(/[$,]/g, '') :
                            //         data;
                            // }
                        }
                    }
                }
            ],
            dom:
                "<'dt-toolbar'<'col-xs-12 col-sm-6 text-right' ><'col-sm-6 col-xs-12 hidden-xs text-right' Bl>r> t <'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>" ,
            oLanguage: {
                sSearch:
                    "<span class='input-group-addon'><i class='glyphicon glyphicon-search'></i></span> ",
                sLengthMenu: "_MENU_"
            },
            initComplete: (settings, json) => {
                timer(10).subscribe(it=>{
                    $('.DTFC_LeftHeadWrapper').css('margin-top','-6px');
                    // location.href='#today'
                    // $("#today").focus();
                    // let x = 35+50+80+60+80+60+35;
                    // let x = $(".DTFC_LeftWrapper").width();
                    let x = 0;
                    let c = 0;
                    for (let i = 0; i < this.data.ptcpStDtMondayToPtcpEndDtSundayFullDay.length; i++) {
                        if (moment(this.data.ptcpStDtMondayToPtcpEndDtSundayFullDay[i]).format('YYYYMMDD') === moment().format('YYYYMMDD')) {
                            break;
                        }
                        x+=59;
                        c++;
                    }
                    x-= (59+59);
                    $(".dataTables_scrollBody").scrollLeft(x);
                });
            }
        } );

        if (this.progress) {
            this.progress.out();
        }
        // $("#datatableContainer table:first-child").DataTable(this.options);
        // $("#example").DataTable(this.options);
        // timer(1000).subscribe(it=>this.table.render());
    }

    makeMoment(data?: Date){
        return moment(data);
    }
}
