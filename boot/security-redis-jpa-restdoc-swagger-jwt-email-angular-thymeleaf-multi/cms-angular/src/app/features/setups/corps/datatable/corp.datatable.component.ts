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
import {CoreCorpGrp, CorpGrpAuth} from '@generate/models';
import {PageRequest} from '@app/models/PageRequest';
import {DatatableComponent} from '@app/features/datatable/datatable.component';
import {MomentService} from '@app/services/date/moment.service';
import {CorpModalComponent} from "@app/features/setups/corps/modal/corp-modal.component";

declare let $: any;

export interface Where {
    search: string;
}


@Component({
    selector: 'web-core-corp-datatable',
    templateUrl: 'corp.datatable.component.html',
    styleUrls: ['corp.datatable.component.css']
})
export class CorpDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Output() response = new EventEmitter<Page<CoreCorpGrp>>();
    @Input() public where: CoreCorpGrp = {} as CoreCorpGrp;
    @Input() public strip = false;
    // @Input() public tableClass = '';
    @Input() public pageLength = 10;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(CorpModalComponent) modal: CorpModalComponent;

    constructor(private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    ngOnInit() {
        // console.log('tableHeaderStyle', this.tableHeaderStyle);
        this.options = {
            // ajax: 'assets/api/tables/datatables.standard.json',
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);
                const url = '/setups/corps';
                this.api.get<Page<CoreCorpGrp>>(url, {params: where})
                    .subscribe((responseData: Page<CoreCorpGrp>) => {
                        if (responseData.content && responseData.content.length) {
                            responseData.content.forEach(it => {
                                it.regDt = this.momentService.moment(it.regDt).toDate();
                            });
                        }
                        const cdata = Object.assign(new Page<CoreCorpGrp>(), responseData);
                        this.response.emit(cdata);
                        callback(cdata);
                    }, this.api.errorHandler.bind(this));
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
                    data: 'No', defaultContent: '',
                    render: (data, display, row, meta) => String(this.table.getPageInfo().recordsDisplay - meta.row - this.table.getPageInfo().start)
                },
                {data: 'corpGrpSeq', visible: true, render: (data) => data ? String(data) : ''},
                {data: 'corpGrpNm', defaultContent: '', render: (data) => data ? String(data) : ''},
                {
                    data: 'corpGrpAuths', defaultContent: '', render: (data: CorpGrpAuth[]) => {
                        return data.map(it => it.auth?.authNm + ' (' + it.authId + ')').join(',');
                    }
                },
                {
                    data: 'regDt',
                    defaultContent: '',
                    render: (date) => this.momentService.moment(date).format('YYYY.MM.DD')
                },
            ],
            buttons: this.strip ? undefined : [
                {
                    extend: 'excel',
                    text: this.i18n.getTranslation('Excel'),
                    className: 'btn btn-secondary btn-sm mr-1'
                    // fn: function() {
                    //   this.submit();
                    // },
                }
            ],
            rowCallback: (row: Node, data: CoreCorpGrp, index: number) => {
                $('td', row).unbind('click');
                $('td', row).bind('click', (e) => {
                    this.modal.show(data);
                    // const actionStr: string = $(e.target).attr("action");
                    // if (actionStr == "Update") {
                    //     this.accountModal.accountSetData(data);
                    //     this.accountModal.modalOpen();
                    // }
                });
                return row;
            }
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

}
