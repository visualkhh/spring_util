import {Component, Input, ElementRef, AfterContentInit, OnInit, OnChanges, SimpleChanges, AfterContentChecked, Output, EventEmitter, ViewChild} from '@angular/core';
// import 'script-loader!jszip/dist/jszip.min.js';
// import 'script-loader!smartadmin-plugins/datatables/datatables.min.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
import {I18nService, JsonApiService} from '@web-core/app/services';
import {Page} from '@web-core/app/models/Page';
import {Adm, Brd, BrdCateCd} from '@web-core-generate/models';
import {PageRequest} from '@web-core/app/models/PageRequest';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {BrdModalComponent} from '@web-core/app/features/brds/modal/brd-modal.component';
declare let $: any;

export interface Where {
    search: string;
}



@Component({
    selector: 'web-core-brd-datatable[type]',
    templateUrl: 'brd.datatable.component.html',
    styleUrls: ['brd.datatable.component.css']
})
export class BrdDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Output() response = new EventEmitter<Page<Brd>>();
    @Input() type: BrdCateCd;
    @Input() public where: Where = {search: ''};
    @Input() public strip = false;
    // @Input() public tableClass = '';
    @Input() public pageLength = 10;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(BrdModalComponent) modal: BrdModalComponent;

    constructor(private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    ngOnInit() {
        // console.log('tableHeaderStyle', this.tableHeaderStyle);
        this.options = {
            // ajax: 'web-core-assets/api/tables/datatables.standard.json',
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);
                let url = '';
                if (this.type === 'BCC001') {
                    url = '/brds/notices';
                } else if (this.type === 'BCC004') {
                    url = '/brds/qas';
                }
                this.api.get<Page<Brd>>(url, {params: where})
                    .subscribe((responseData: Page<Brd>) => {
                        if (responseData.content && responseData.content.length) {
                            responseData.content.forEach(it => {
                                it.regDt = this.momentService.moment(it.regDt).toDate();
                            });
                        }
                        const cdata = Object.assign(new Page<Brd>(), responseData);
                        this.response.emit(cdata);
                        callback(cdata);
                    }, this.api.errorHandler.bind(this));
            },
            serverSide: true,
            paging: true,
            pageLength: this.pageLength,
            ordering: false,
            lengthMenu: [[10, 25, 50, 100, 150, -1], [10, 25, 50, 100, 150, 'All']],
            columns: [
                {
                    data: 'No', defaultContent: '',
                    render: (data, display, row, meta) => {
                        return this.table.getPageInfo().recordsDisplay - meta.row - this.table.getPageInfo().start;
                    }
                },
                {data: 'brdSeq', visible: false},
                {data: 'cateCd', visible: false},
                {
                    data: 'titl', class: '.ellipsis', render: (data: string, display: string, row: Brd) => {
                        return data + ' ' + (row.brds.length > 0 ? '<span class="badge badge-info float-right">' + row.brds.length + '</span>' : '');
                    }
                },
                {data: 'cont', visible: false},
                {data: 'useCd', visible: false},
                {
                    data: 'adm', defaultContent: '', render: (data: Adm, display: string, row: Brd) => {
                        return data.admNm;
                    }
                },
                {data: 'regDt', defaultContent: '', render: (date) => this.momentService.moment(date).format('YYYY.MM.DD')},
            ],
            buttons: this.strip ? undefined : [
                {
                    extend: 'excel',
                    text: this.i18n.getTranslation('Excel'),
                    className: 'btn btn-secondary mr-1'
                    // fn: function() {
                    //   this.submit();
                    // },
                }
            ],
            rowCallback: (row: Node, data: Brd, index: number) => {
                $('td', row).unbind('click');
                $('td', row).bind('click', (e) => {
                    this.modal.show(data);
                    const actionStr: string = $(e.target).attr('action');
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
