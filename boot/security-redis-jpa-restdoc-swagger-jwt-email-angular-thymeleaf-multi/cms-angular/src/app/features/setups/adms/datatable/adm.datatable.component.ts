import {
    AfterContentChecked,
    AfterContentInit,
    Component,
    ElementRef,
    EventEmitter,
    Input,
    OnChanges,
    OnInit,
    Output,
    SimpleChanges,
    ViewChild
} from '@angular/core';
// import 'script-loader!jszip/dist/jszip.min.js';
// import 'script-loader!smartadmin-plugins/datatables/datatables.min.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
import {I18nService, JsonApiService} from '@app/services';
import {Page} from '@app/models/Page';
import {CoreAdm, CoreAdmAuth, BrdCateCd, CoreCorpGrp, UseCd} from '@generate/models';
import {PageRequest} from '@app/models/PageRequest';
import {DatatableComponent} from '@app/features/datatable/datatable.component';
import {MomentService} from '@app/services/date/moment.service';
import {MomentPipe} from '@app/shareds/pipes/date/moment.pipe';
import {Router} from '@angular/router';
import {AdmModalComponent} from '@app/features/setups/adms/modal/adm-modal.component';
import {BasicModalComponent} from '@app/shareds/modals/basic-modal/basic-modal.component';

declare let $: any;

@Component({
    selector: 'web-core-adm-datatable',
    templateUrl: 'adm.datatable.component.html',
    styleUrls: ['adm.datatable.component.css']
})
export class AdmDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Input() type: BrdCateCd;
    @Input() public where: CoreAdm;
    @Input() public strip = false;
    // @Input() public tableClass = '';
    @Input() public pageLength = 10;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(AdmModalComponent) modal: AdmModalComponent;
    @ViewChild(BasicModalComponent) historyModal: BasicModalComponent;

    constructor(private momentPipe: MomentPipe, private router: Router, private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    ngOnInit() {
        this.options = {
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);

                this.api.get<Page<CoreAdm>>('/setups/adms', {params: where}).subscribe((responseData: Page<CoreAdm>) => {
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
                    // const cdata = Object.assign(new Page<PtntInfo>(), responseData);
                    // callback(cdata);
                }, this.api.errorHandler.bind(this.api));
            },
            serverSide: true,
            paging: true,
            pageLength: this.pageLength,
            ordering: false,
            lengthMenu: [[10, 25, 50, 100, 150, -1], [10, 25, 50, 100, 150, 'All']],
            language: {
                info: '_START_ to _END_ of _TOTAL_',
                // infoEmpty: 'Show 0 to 0 of 0 entries',
                paginate: {
                    previous: '◀︎',
                    next: '▶',
                }
            },
            columns: [
                {
                    data: 'No',
                    defaultContent: '',
                    render: (data, display, row, meta) => String(this.table.getPageInfo().recordsDisplay - meta.row - this.table.getPageInfo().start)
                },
                {
                    data: 'admSeq',
                    className: 'text-right',
                    defaultContent: '',
                    visible: false,
                    render: (data) => data ? String(data) : ''
                },
                {
                    data: 'admLginId',
                    className: 'text-left',
                    defaultContent: '',
                    render: (data) => data ? String(data) : ''
                },
                {data: 'admNm', className: 'text-left', defaultContent: '', render: (data) => data ? String(data) : ''},
                {
                    data: 'companyNm',
                    className: 'text-left',
                    defaultContent: '',
                    render: (data) => data ? String(data) : ''
                },
                {data: 'email', className: 'text-left', defaultContent: '', render: (data) => data ? String(data) : ''},
                {
                    data: 'jobCd',
                    className: 'text-left',
                    defaultContent: '',
                    render: (data) => this.i18n.getTranslation(data)
                },
                {
                    data: 'corpGrp', className: 'text-left', defaultContent: '',
                    render: (data: CoreCorpGrp) => {
                        return data?.corpGrpNm || '-';
                    }
                },
                {
                    data: 'admAuths', className: 'text-left', defaultContent: '',
                    render: (data: CoreAdmAuth[]) => {
                        return data.map(it => it.auth?.authNm + ' (' + it.authId + ')').join(',');
                    }
                },
                {
                    data: 'startDt',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data: Date, display: string, row: CoreAdm) => {
                        return (row.startDt ? this.momentPipe.transform(row.startDt, 'YYYY-MM-DD') : '-') + ' ~ ' + (row.endDt ? this.momentPipe.transform(row.endDt, 'YYYY-MM-DD') : '-');
                    }
                },
                {
                    data: 'useCd',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data: UseCd) => {
                        return '<span class="center-block p-2 badge ' + (UseCd.USE001 === data ? 'badge-success' : 'badge-light') + '">' + this.i18n.getTranslation(data) + '</span>';
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
                    render: (columnData: any, type: string, data: CoreAdm) => {
                        return '<button type="button" class="btn btn-secondary btn-sm" action="accessHistory"><i class="icon-append fa fa-history" action="accessHistory"></i></button>';
                    }
                }
            ],
            rowCallback: (row: Node, data: CoreAdm, index: number) => {
                $('td', row).unbind('click');
                $('td', row).bind('click', (e) => {
                    const action = $(e.target).attr('action');
                    if ('accessHistory' === action) {
                        // this.historyModal.show();
                    } else {
                        // this.modal.show(data.admSeq);
                    }
                    // this.router.navigate([`/patients/${data.ptntSeq}`]);
                });
                return row;
            },
            buttons: [
                {
                    extend: 'excel',
                    text: this.i18n.getTranslation('Excel'),
                    className: 'btn btn-secondary btn-sm mr-1'
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
