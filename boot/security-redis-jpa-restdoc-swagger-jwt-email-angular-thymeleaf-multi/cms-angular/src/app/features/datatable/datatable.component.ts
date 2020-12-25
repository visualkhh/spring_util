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
    SimpleChanges
} from '@angular/core';
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/dataTables.buttons.min.js";
// import 'script-loader!jszip/dist/jszip.min.js';
// import "script-loader!smartadmin-plugins/datatables/datatables-bundle.min.js";
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/buttons.html5.min.js";
// import 'script-loader!smartadmin-plugins/datatables/datatables.min.js';
// import 'script-loader!datatables.net/js/jquery.dataTables.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
// import 'script-loader!datatables.net/js/jquery.dataTables.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
// import "script-loader!smartadmin-plugins/datatables-bundle/datatables.min.js";
declare var $: any;

@Component({
    selector: 'web-core-datatable',
    template: `
            <table class="dataTable responsive {{tableClass}}" width="{{width}}">
                <ng-content (contentChange)="doSomething()"></ng-content>
            </table>
    `,
    // require("smartadmin-plugins/datatables/Buttons-1.2.4/css/buttons..min.css"),
    // styles: [require("smartadmin-plugins/datatables/datatables.min.css")]
    // styleUrls: ["../../../assets/css/bootstrap.min.css", "datatable.component.css"]
    styleUrls: ['./datatable.component.css']
})
export class DatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    @Input() public options: any;
    @Input() public filter: any;
    @Input() public detailsFormat: any;

    @Input() public paginationLength = true;
    @Input() public pageNavigation = true;
    @Input() public pageInfo = true;
    @Input() public columnsHide: boolean;
    @Output() destroyCompleted = new EventEmitter<any>();
    @Input() public tableClass: string;
    @Input() public width = '100%';
    private _dataTable: any;
    private tableHeaderElement: any;

    constructor(private el: ElementRef) {
    }

    ngOnInit() {
        // alert(this.el.nativeElement.innerHTML)
        // if (this.options['autoRender'] === undefined) {
        //   this.options['autoRender'] = true;
        // }
        // if (this.options['autoRender']){
        //   this.render();
        // }
        this.render();
    }

    doSomething() {
        // console.log('-DatatableComponent--doSomething-')
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

    render() {

        if (this.options.targetContainerSelctor) {
            $(this.el.nativeElement).empty();
            // alert($(this.options.targetContainerSelctor).html());
            const clone = $(this.options.targetContainerSelctor).clone();
            $(this.el.nativeElement).append(clone);
            clone.show();
        }
        this.tableHeaderElement = $(this.el.nativeElement.children[0]);

        let options = this.options || {};
        let toolbar = '';
        let footerToolbar = '';
        if (options.buttons) {
            toolbar += 'B';
        }
        if (this.paginationLength) {
            toolbar += 'l';
        }
        if (this.columnsHide) {
            toolbar += 'C';
        }
        if (this.pageInfo) {
            footerToolbar += 'i';
        }
        if (this.pageNavigation) {
            footerToolbar += 'p';
        }

        if (typeof options.ajax === 'string') {
            const url = options.ajax;
            options.ajax = {
                url
                // complete: function (xhr) {
                //
                // }
            };
        }

        // https://datatables.net/reference/option/dom
        // Bfrtip
        const dom = options.dom;
        options = $.extend(options, {
            // dom:
            //   "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs text-right'" +
            //   toolbar +
            //   ">r>" +
            //   "t" +
            //   "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
            // dom: '<"col-12 text-right"lfrB>' +
            //     't' +
            //     '<"dt-toolbar-footer fg-toolbar ui-toolbar ui-widget-header ui-helper-clearfix ui-corner-bl ui-corner-br"ip>',
            dom:
                '<"w-100 d-flex mb-1 justify-content-end"' + toolbar + '>' +
                // '<"w-100 float-right"' + toolbar + '>' +
                '<"w-100 overflow-auto" t>' +
                '<"w-100 d-flex mt-1 justify-content-between"' + footerToolbar + ' >',
            // dom: 'Bfrtip',
            oLanguage: {
                sSearch:
                    '<span class=\'input-group-addon\'><i class=\'glyphicon glyphicon-search\'></i></span> ',
                sLengthMenu: '_MENU_'
            },
            autoWidth: false,
            retrieve: true,
            responsive: true,
            initComplete: (settings, json) => {
                this.tableHeaderElement
                    .parent()
                    .find('.input-sm')
                    .removeClass('input-sm')
                    .addClass('input-md');
                $('.dataTables_length > label').addClass('m-0');
            }
        });

        // if (dom) {
        //   options.dom = dom;
        // }


        this._dataTable = this.tableHeaderElement.DataTable(options);

        if (this.filter) {
            // Apply the filter
            this.tableHeaderElement.on('keyup change', 'thead th input[type=text]', function () {
                this._dataTable
                    .column(
                        $(this)
                            .parent()
                            .index() + ':visible'
                    )
                    .search(this.value)
                    .draw();
            });
        }

        if (!toolbar) {
            this.tableHeaderElement
                .parent()
                .find('.dt-toolbar')
                .append(
                    '<div class="text-right"><img src="assets/img/logo.gif" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>'
                );
        }

        if (this.detailsFormat) {
            const format = this.detailsFormat;
            this.tableHeaderElement.on('click', 'td.details-control', function () {
                const tr = $(this).closest('tr');
                const row = this._dataTable.row(tr);
                if (row.child.isShown()) {
                    row.child.hide();
                    tr.removeClass('shown');
                } else {
                    row.child(format(row.data())).show();
                    tr.addClass('shown');
                }
            });
        }
    }

    public getDataTable() {
        return this._dataTable;
    }

    public getAjax() {
        return this._dataTable.ajax;
    }

    public ajaxReload() {
        return this._dataTable.ajax.reload();
    }

    public getPageInfo() {
        return this._dataTable.page.info();
    }

    public destroy() {
        this._dataTable.destroy();
        $(this.el.nativeElement).empty();
        this.destroyCompleted.emit();

    }

    public draw() {
        if (this._dataTable) {
            this._dataTable.draw();
        }
    }

    public clearAndSetData(data: any) {
        if (this._dataTable) {
            this._dataTable.fnClearTable();
            this._dataTable.fnAddData(data);
        }
    }


}
