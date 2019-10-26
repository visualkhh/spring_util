import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService, ValidationService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, GameRst, GameSetDayRst, GameSetRst, GameSetWeekRst, User, UserDetails} from "@app/model/commomModels";
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
    selector: 'app-participant-concentration',
    templateUrl: './participant-concentration.component.html',
    styleUrls: ['./participant-concentration.component.css'],
    providers: [CmsCommonService]
})


export class ParticipantConcentrationComponent implements OnInit {
    @ViewChild(ConformityDetailsModalComponent) modal: ConformityDetailsModalComponent;
    @Input() user: User;
    private startDt: Date;
    private endDt: Date;
    private i18n: I18nService;
    private data: number[][][];
    // private data: [number[][]];
    // private data: any;
    //let data = [[1196463600000, 0], [1196550000000, 0], [1196636400000, 0], [1196722800000, 77], [1196809200000, 3636], [1196895600000, 3575], [1196982000000, 2736], [1197068400000, 1086], [1197154800000, 676], [1197241200000, 1205], [1197327600000, 906], [1197414000000, 710], [1197500400000, 639], [1197586800000, 540], [1197673200000, 435], [1197759600000, 301], [1197846000000, 575], [1197932400000, 481], [1198018800000, 591], [1198105200000, 608], [1198191600000, 459], [1198278000000, 234], [1198364400000, 1352], [1198450800000, 686], [1198537200000, 279], [1198623600000, 449], [1198710000000, 468], [1198796400000, 392], [1198882800000, 282], [1198969200000, 208], [1199055600000, 229], [1199142000000, 177], [1199228400000, 374], [1199314800000, 436], [1199401200000, 404], [1199487600000, 253], [1199574000000, 218], [1199660400000, 476], [1199746800000, 462], [1199833200000, 500], [1199919600000, 700], [1200006000000, 750], [1200092400000, 600], [1200178800000, 500], [1200265200000, 900], [1200351600000, 930], [1200438000000, 1200], [1200524400000, 980], [1200610800000, 950], [1200697200000, 900], [1200783600000, 1000], [1200870000000, 1050], [1200956400000, 1150], [1201042800000, 1100], [1201129200000, 1200], [1201215600000, 1300], [1201302000000, 1700], [1201388400000, 1450], [1201474800000, 1500], [1201561200000, 546], [1201647600000, 614], [1201734000000, 954], [1201820400000, 1700], [1201906800000, 1800], [1201993200000, 1900], [1202079600000, 2000], [1202166000000, 2100], [1202252400000, 2200], [1202338800000, 2300], [1202425200000, 2400], [1202511600000, 2550], [1202598000000, 2600], [1202684400000, 2500], [1202770800000, 2700], [1202857200000, 2750], [1202943600000, 2800], [1203030000000, 3245], [1203116400000, 3345], [1203202800000, 3000], [1203289200000, 3200], [1203375600000, 3300], [1203462000000, 3400], [1203548400000, 3600], [1203634800000, 3700], [1203721200000, 3800], [1203807600000, 4000], [1203894000000, 4500]];

    constructor(private route: ActivatedRoute, private cmsCommonService: CmsCommonService, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private validationService: ValidationService) {
        this.i18n = I18nService.getInstance();
    }

    ngOnInit() {
        console.log(this.user);
        // this.selectStartDate(this.user.ptcpStDt);
        // this.selectEndDate(this.user.ptcpEndDt);
        // this.selectDate(new Date());
        // this.where = {fromDate: this.cmsCommonService.getMondayOfWeek(), toDate: this.cmsCommonService.getSundayOfWeek()};
        // this.betweenDate = this.cmsCommonService.betweenDates(this.where.fromDate, this.where.toDate, 'days', 1);
        // timer(5000).subscribe(it => this.table.render());
        this.search();
    }

    options = {
        xaxis: {
            mode: "time",
            tickLength: 5,
            tickFormatter : function(v) {
                return moment(v).format('MM.DD');
            }

        },
        yaxis : {
            // tickFormatter : function(v) {
            //     return v + " cm";
            // }
        },
        series: {
            lines: {
                show: true,
                lineWidth: 1,
                fill: false,
                fillColor: {
                    colors: [{
                        opacity: 0.1
                    }, {
                        opacity: 0.15
                    }]
                }
            },
            points: {show: true},
            shadowSize: 0
        },
        selection: {
            mode: "x"
        },
        grid: {
            hoverable: true,
            clickable: true,
            tickColor: '#efefef',
            borderWidth: 0,
            borderColor: '#efefef',
        },
        tooltip: true,
        tooltipOpts: {
            content: "<b>%x</b>: <span>%y</span>",
            dateFormat: "%Y-%m-%d",
            defaultTheme: false
        },
        colors: ['#6595b4', '#FF00b4'],
    };
    // data = [
    //     {
    //         data: [[0, 0], [0.5, 0.479425538604203], [1, 0.8414709848078965], [1.5, 0.9974949866040544], [2, 0.9092974268256817], [2.5, 0.5984721441039564], [3, 0.1411200080598672], [3.5, -0.35078322768961984], [4, -0.7568024953079282], [4.5, -0.977530117665097], [5, -0.9589242746631385], [5.5, -0.7055403255703919], [6, -0.27941549819892586], [6.5, 0.21511998808781552], [7, 0.6569865987187891], [7.5, 0.9379999767747389], [8, 0.9893582466233818], [8.5, 0.7984871126234903], [9, 0.4121184852417566], [9.5, -0.0751511204618093], [10, -0.5440211108893698], [10.5, -0.87969575997167], [11, -0.9999902065507035], [11.5, -0.8754521746884285], [12, -0.5365729180004349], [12.5, -0.06632189735120068], [13, 0.4201670368266409], [13.5, 0.803784426551621], [14, 0.9906073556948704], [14.5, 0.934895055524683], [15, 0.6502878401571168], [15.5, 0.2064674819377966]],
    //     }
    // ];
    // colors = {
    //     "chartBorderColor": "#efefef",
    //     "chartGridColor": "#DDD",
    //     "charMain": "#E24913",
    //     "chartSecond": "#6595b4",
    //     "chartThird": "#FF9F01",
    //     "chartFourth": "#7e9d3a",
    //     "chartFifth": "#BD362F",
    //     "chartMono": "#000"
    // };
    // options = {
    //     series: {
    //         lines: {
    //             show: true
    //         },
    //         points: {
    //             show: true
    //         }
    //     },
    //     grid: {
    //         hoverable: true,
    //         clickable: true,
    //         tickColor: this.colors.chartBorderColor,
    //         borderWidth: 0,
    //         borderColor: this.colors.chartBorderColor
    //     },
    //     tooltip: true,
    //     tooltipOpts: {
    //         content : "Value <b>$x</b> Value <span>$y</span>",
    //         defaultTheme: false
    //     },
    //     colors: [this.colors.chartSecond, this.colors.chartFourth],
    //     yaxis: {
    //         min: -1.1,
    //         max: 1.1
    //     },
    //     xaxis: {
    //         min: 0,
    //         max: 15
    //     }
    // };

    setGraph() {
    }


    public search() {
        let action = new ApiAction<GameSetRst[]>(this.router.url + "/concentration");
        let where = {};
        if(this.startDt) {
            where['startDt'] = moment(this.startDt).toISOString();
        }
        if(this.startDt) {
            where['endDt'] = moment(this.endDt).toISOString();
        }
        action.param = where;
        action.success = (data: GameSetRst[]) => {
            if (data && data.length) {
                let container = new Array<number[]>();
                // let container2 = new Array<number[]>();
                data.forEach(it => {
                    it.msmtStDt = moment(it.msmtStDt).toDate();
                    it.msmtEndDt = moment(it.msmtEndDt).toDate();
                    container.push([it.msmtEndDt.getTime(), it.cent]);
                    // container2.push([it.msmtEndDt.getTime(), it.cent+2])
                });
                this.data = [container];
                // this.data = [container,container2];
            }
        }
        this.jsonApiService.get(action);
        // this.setGraph();
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
        this.startDt = data; //this.cmsCommonService.getMondayOfWeek(data);
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
        this.endDt = m.add(1, 'day').toDate(); //this.cmsCommonService.getSundayOfWeek(data);
    }
}
