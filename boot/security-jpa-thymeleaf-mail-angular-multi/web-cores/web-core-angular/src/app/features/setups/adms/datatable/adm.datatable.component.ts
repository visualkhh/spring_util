import {Component, Input, ElementRef, AfterContentInit, OnInit, OnChanges, SimpleChanges, AfterContentChecked, Output, EventEmitter, ViewChild} from '@angular/core';
// import 'script-loader!jszip/dist/jszip.min.js';
// import 'script-loader!smartadmin-plugins/datatables/datatables.min.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
import {I18nService, JsonApiService} from '@web-core/app/services';
import {Page} from '@web-core/app/models/Page';
import {
    ActionCd,
    ActionHistory,
    Adm, AdmAuth,
    Brd,
    BrdCateCd,
    CorpGrp,
    GenderCd,
    ProcStatCd,
    PtntInfo,
    UseCd
} from '@web-core-generate/models';
import {PageRequest} from '@web-core/app/models/PageRequest';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {BrdModalComponent} from '@web-core/app/features/brds/modal/brd-modal.component';
import {DiseaseModalComponent} from '@web-core/app/features/patients/modal/disease/disease-modal.component';
import {MomentPipe} from '@web-core/app/pipes/date/moment.pipe';
import {Router} from '@angular/router';
import {AdmModalComponent} from '@web-core/app/features/setups/adms/modal/adm-modal.component';
import {BasicModalComponent} from '@web-core/app/features/modals/basic-modal/basic-modal.component';
import {HistoryDatatableComponent} from '@web-core/app/features/auths/history/datatable/history.datatable.component';
declare let $: any;

// export interface Where extends Adm {
//     companyNm: string | undefined;
//     admNm: string | undefined;
//     admLginId: string | undefined;
//     useCd: UseCd | string;
// }

@Component({
    selector: 'web-core-adm-datatable',
    templateUrl: 'adm.datatable.component.html',
    styleUrls: ['adm.datatable.component.css']
})
export class AdmDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Output() response = new EventEmitter<Page<Brd>>();
    @Input() type: BrdCateCd;
    @Input() public where: Adm;
    @Input() public strip = false;
    // @Input() public tableClass = '';
    @Input() public pageLength = 10;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(AdmModalComponent) modal: AdmModalComponent;
    @ViewChild(BasicModalComponent) historyModal: BasicModalComponent;
    @ViewChild(HistoryDatatableComponent) historyDatatable: HistoryDatatableComponent;

    constructor(private momentPipe: MomentPipe, private router: Router, private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    ngOnInit() {
        this.options = {
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);

                this.api.get<Page<Adm>>('/setups/adms', {params: where}).subscribe((responseData: Page<Adm>) => {
                    if (responseData.content && responseData.content.length) {
                        responseData.content.forEach(it => {
                            if (it.regDt) {
                                it.regDt = this.momentService.moment(it.regDt).toDate();
                            }
                            if (it.startDt) {
                                it.startDt = this.momentService.moment(it.startDt).toDate();
                            }
                            if (it.endDt) {
                                it.endDt = this.momentService.moment(it.endDt).toDate();
                            }
                        });
                    }
                    const cdata = Object.assign(new Page<PtntInfo>(), responseData);
                    callback(cdata);
                }, this.api.errorHandler.bind(this.api));
            },
            serverSide: true,
            paging: true,
            pageLength: this.pageLength,
            ordering: false,
            lengthMenu: [[10, 25, 50, 100, 150, -1], [10, 25, 50, 100, 150, 'All']],
            columns: [
                {
                    data: 'No',
                    defaultContent: '',
                    render: (data, display, row, meta) => this.table.getPageInfo().recordsDisplay - meta.row - this.table.getPageInfo().start
                },
                {data: 'admSeq', className: 'text-right', defaultContent: '', visible: false},
                {data: 'admLginId', className: 'text-left', defaultContent: ''},
                {data: 'admNm', className: 'text-left', defaultContent: ''},
                {data: 'companyNm', className: 'text-left', defaultContent: ''},
                {data: 'email', className: 'text-left', defaultContent: ''},
                {data: 'jobCd', className: 'text-left', defaultContent: '',  render: (data) => this.i18n.getTranslation(data)},
                {data: 'corpGrp', className: 'text-left', defaultContent: '',
                    render: (data: CorpGrp) => {
                        return data?.corpGrpNm || '-';
                    }
                },
                {data: 'admAuths', className: 'text-left', defaultContent: '',
                    render: (data: AdmAuth[]) => {
                        return data.map(it => it.auth?.authNm + ' (' + it.authId + ')').join(',');
                    }
                },
                {
                    data: 'startDt',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data: Date, display: string, row: Adm) => {
                        return (row.startDt ? this.momentPipe.transform(row.startDt, 'YYYY-MM-DD') : '-') + ' ~ ' + (row.endDt ? this.momentPipe.transform(row.endDt, 'YYYY-MM-DD') : '-');
                    }
                },
                {
                    data: 'useCd',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data: UseCd) => {
                        return '<span class="center-block p-2 badge ' + ('USE001' === data ? 'badge-success' : 'badge-light') + '">' + this.i18n.getTranslation(data) + '</span>';
                    }
                },
                {
                    data: 'regDt',
                    className: 'text-center',
                    defaultContent: '',
                    render: (date) => this.momentPipe.transform(date)
                },
                {
                    data: null,
                    className: 'text-center',
                    defaultContent: '',
                    render: (columnData: any, type: string, data: Adm) => {
                        return '<button type="button" class="btn btn-secondary btn-sm" action="accessHistory"><i class="icon-append fa fa-history" action="accessHistory"></i></button>';
                    }
                }
            ],
            rowCallback: (row: Node, data: Adm, index: number) => {
                $('td', row).unbind('click');
                $('td', row).bind('click', (e) => {
                    const action = $(e.target).attr('action');
                    if ('accessHistory' === action) {
                        this.historyDatatable.draw({admSeq: data.admSeq});
                        this.historyModal.show();
                    } else {
                        this.modal.show(data.admSeq);
                    }
                    // this.router.navigate([`/patients/${data.ptntSeq}`]);
                });
                return row;
            },
            buttons: [
                {
                    extend: 'excel',
                    text: this.i18n.getTranslation('Excel'),
                    className: 'btn btn-secondary mr-1'
                }
            ]
        };
    }


    public search() {
        this.table.draw();
    }

    public draw() {
        this.table.draw();
    }

    doSomething() {
    }

    ngOnChanges(changes: SimpleChanges) {
        // console.log('-DatatableComponent--ngOnChanges-')
    }

    ngAfterContentInit(): void {
        // console.log('-DatatableComponent--ngAfterContentInit-')
    }

    ngAfterContentChecked(): void {
        // console.log('-DatatableComponent--ngAfterContentChecked-')
    }

    admComplet($event: any) {
        this.draw();
    }
}
