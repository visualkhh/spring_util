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
import {ActionCd, ActionHistory} from '@web-core-generate/models';
import {I18nService, JsonApiService} from '@web-core/app/services';
import {Page} from '@web-core/app/models/Page';
import {Adm, Brd} from '@web-core-generate/models';
import {PageRequest} from '@web-core/app/models/PageRequest';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {BrdModalComponent} from '@web-core/app/features/brds/modal/brd-modal.component';

declare let $: any;

export interface Where {
    admSeq: number|undefined;
    actCds: ActionCd[]|undefined;
}


@Component({
    selector: 'web-core-history-datatable',
    templateUrl: 'history.datatable.component.html',
    styleUrls: ['history.datatable.component.css']
})
export class HistoryDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Output() response = new EventEmitter<Page<ActionHistory>>();
    @Input() public where = {} as Where | any;
    @Input() public strip = false;
    @Input() public pageLength = 10;
    @Input() public type: 'SIGN'|'URL' = 'SIGN';

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(BrdModalComponent) modal: BrdModalComponent;

    constructor(private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    // datatable language: https://datatables.net/reference/option/language
    ngOnInit() {
        // console.log('tableHeaderStyle', this.tableHeaderStyle);
        this.options = {
            // ajax: 'web-core-assets/api/tables/datatables.standard.json',
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);
                const url = undefined === this.where.admSeq ? '/auths-web-core/history' : `/setups/adms/history/${this.where.admSeq}`;
                this.api.get<Page<ActionHistory>>(url, {params: where})
                    .subscribe((responseData: Page<ActionHistory>) => {
                        if (responseData.content && responseData.content.length) {
                            responseData.content.forEach(it => {
                                it.regDt = this.momentService.moment(it.regDt).toDate();
                            });
                        }
                        const cdata = Object.assign(new Page<ActionHistory>(), responseData);
                        this.response.emit(cdata);
                        callback(cdata);
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
                    data: 'No', defaultContent: '', visible: false,
                    render: (data, display, row, meta) => {
                        return this.table.getPageInfo().recordsDisplay - meta.row - this.table.getPageInfo().start;
                    }
                },
                {data: 'actSeq', visible: false},
                {data: 'admSeq', visible: false},
                {
                    data: 'actCd', defaultContent: '', render: (data: ActionCd, display: string, row: ActionHistory) => {
                        return this.i18n.getTranslation(data);
                    }
                },
                {data: 'url', visible: 'URL' === this.type, render: (data: string = '', display: string, row: ActionHistory) => {
                        return `<input type='text' value='${data}'>`;
                    }
                },
                {data: 'request', visible: 'URL' === this.type, render: (data: string = '', display: string, row: ActionHistory) => {
                        return `<input type='text' value='${data}'>`;
                    }
                },
                {data: 'response', visible: 'URL' === this.type, render: (data: string = '', display: string, row: ActionHistory) => {
                        return `<input type='text' value='${data}'>`;
                    }
                },
                {
                    data: 'regDt',
                    defaultContent: '',
                    render: (data: Date, display: string, row: ActionHistory) => this.momentService.moment(data).format('YYYY-MM-DD HH:mm:ss')
                },
                {data: 'ip', defaultContent: ''},
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
                // $('td', row).unbind('click');
                // $('td', row).bind('click', (e) => {
                //     this.modal.show(data);
                // });
                // return row;
            }
        };
    }

    public search(where: Where | any = this.where) {
        this.where = where;
        this.table.draw();
    }

    public draw(where: Where | any = this.where) {
        this.where = where;
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
