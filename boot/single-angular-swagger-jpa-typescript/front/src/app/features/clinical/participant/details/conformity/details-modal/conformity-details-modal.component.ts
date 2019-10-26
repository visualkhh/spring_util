import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ApiAction, JsonApiService, UserService} from "@app/core/services";
import {NotificationService} from "@app/core/services/notification.service";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {ValidationService} from "@app/core/services/validation.service";
import {Brd, BrdCateCd, DataTablePageRequest, DataTablePageResponse, GameRst, GameSetRst, GameSetWeekRst, UserDetails} from "@app/model/commomModels";
import {I18nPipe} from "@app/shared/i18n/i18n.pipe";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-conformity-details-modal',
    templateUrl: './conformity-details-modal.component.html',
    styleUrls: ['./conformity-details-modal.component.css'],
})

export class ConformityDetailsModalComponent implements OnInit {

    @ViewChild('brdModalTemplate') brdTemplate;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    bsModalRef: BsModalRef;
    private userSeq: number;
    private startDt: Date;
    private endDt: Date;

    constructor(private router: Router, private modalService: BsModalService, private jsonApiService: JsonApiService, public userService: UserService, private notificationService: NotificationService, private valid: ValidationService) {
    }


    ngOnInit() {
        console.log('conformity-details-modal.module ngOnInit');
    }
    options = {
        // autoRender: false,
        serverSide: true,
        paging: true,
        ordering: false,
        lengthMenu: [[10, 50, 100, 150, -1], [10, 50, 100, 150, "All"]],
        ajax: (data, callback, setting) => {
            let where = {"size": data.length, "page": data.start / data.length, "draw": data.draw} as DataTablePageRequest;
            // if(this.userSeq) {
            //     where['userSeq'] = this.userSeq;
            // }
            if(this.startDt) {
                where['startDt'] = moment(this.startDt).toISOString();
            }
            if(this.startDt) {
                where['endDt'] = moment(this.endDt).toISOString();
            }
            const action = new ApiAction<DataTablePageResponse<GameSetRst>>(this.router.url+"/conformity/details");
            action.param = where;
            action.success = (data: DataTablePageResponse<GameSetRst>) => {
                if (data.data && data.data.length) {
                    data.data.forEach(it => {
                        it.msmtStDt = moment(it.msmtStDt).toDate();
                        it.msmtEndDt = moment(it.msmtEndDt).toDate();
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
                    return moment(row.endDt).format('YYYY.MM.DD');
                }
            },
            {data: 'msmtStDt', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: GameSetWeekRst) => {
                    return moment(data).format('HH:mm:ss');
                }
            },
            {data: 'msmtEndDt', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: GameSetWeekRst) => {
                    return moment(data).format('HH:mm:ss');
                }
            },
            {data: 'setCd', className: 'text-center col-md-1', defaultContent: "",
                render: (data, display, row: GameSetWeekRst) => {
                    return I18nService.getInstance().getTranslation(data);
                }
            },
        ],
        rowCallback: (row: Node, data: GameSetWeekRst, index: number) => {
            $('td', row).unbind('click');
            $('td', row).bind('click', (e) => {
                let actionStr: string = e.target.getAttribute('action');
            });
            return row;
        }
    };



    public show(userSeq: number, startDt: Date, endDt: Date) {
        this.userSeq = userSeq;
        this.startDt = startDt;
        this.endDt = endDt;
        this.bsModalRef = this.modalService.show(this.brdTemplate);
        // this.table.draw();
    }





}
