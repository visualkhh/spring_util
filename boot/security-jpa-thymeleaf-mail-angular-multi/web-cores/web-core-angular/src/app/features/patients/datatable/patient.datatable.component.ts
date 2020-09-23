import {Component, Input, ElementRef, AfterContentInit, OnInit, OnChanges, SimpleChanges, AfterContentChecked, Output, EventEmitter, ViewChild} from '@angular/core';
// import 'script-loader!jszip/dist/jszip.min.js';
// import 'script-loader!smartadmin-plugins/datatables/datatables.min.js';
// import 'script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js';
// import 'script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js';
import {I18nService, JsonApiService} from '@web-core/app/services';
import {Page} from '@web-core/app/models/Page';
import {Adm, Brd, BrdCateCd, GenderCd, ProcStatCd, PtntInfo} from '@web-core-generate/models';
import {PageRequest} from '@web-core/app/models/PageRequest';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {MomentService} from '@web-core/app/services/date/moment.service';
import {BrdModalComponent} from '@web-core/app/features/brds/modal/brd-modal.component';
import {DiseaseModalComponent} from '@web-core/app/features/patients/modal/disease/disease-modal.component';
import {MomentPipe} from '@web-core/app/pipes/date/moment.pipe';
import {Router} from '@angular/router';
declare let $: any;

export interface Where {
    ptntNm: string | undefined;
    gen: GenderCd | string;
    fromAge: number | undefined;
    toAge: number | undefined;
    hpoProcStatCd: ProcStatCd | string;
    mriProcStatCd: ProcStatCd | string;
    vcfProcStatCd: ProcStatCd | string;
}

@Component({
    selector: 'web-core-patient-datatable',
    templateUrl: 'patient.datatable.component.html',
    styleUrls: ['patient.datatable.component.css']
})
export class PatientDatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    public options: any;

    @Output() response = new EventEmitter<Page<Brd>>();
    @Input() type: BrdCateCd;
    @Input() public where: Where;
    @Input() public strip = false;
    // @Input() public tableClass = '';
    @Input() public pageLength = 10;

    @ViewChild(DatatableComponent) table: DatatableComponent;
    @ViewChild(DiseaseModalComponent) diseaseModalComponent: DiseaseModalComponent;

    constructor(private momentPipe: MomentPipe, private router: Router, private el: ElementRef, private i18n: I18nService, private api: JsonApiService, private momentService: MomentService) {
    }

    ngOnInit() {
        this.options = {
            ajax: (data, callback, setting) => {
                const pageWhere = new PageRequest(data.length, data.start / data.length, data.draw);
                let where = Object.assign({}, this.where);
                where = Object.assign(where, pageWhere);

                this.api.get<Page<PtntInfo>>('/patients', {params: where}).subscribe((responseData: Page<PtntInfo>) => {
                    if (responseData.content && responseData.content.length) {
                        responseData.content.forEach(it => {
                            it.regDt = this.momentService.moment(it.regDt).toDate();
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
                {data: 'ptntSeq', className: 'text-center', defaultContent: ''},
                {data: 'ptntNm', className: 'text-center', defaultContent: ''},
                {
                    data: 'gen',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data) => this.i18n.getTranslation(data)
                },
                {data: 'age', className: 'text-center', defaultContent: ''},
                {
                    data: 'hpoProcStatCd', className: 'text-center', defaultContent: '', render: (data) => {
                        return '<span class="center-block p-2 badge ' + this.getStatClass(data) + '">' + this.i18n.getTranslation(data) + '</span>';
                    }
                },
                {
                    data: 'mriProcStatCd', className: 'text-center', defaultContent: '', render: (data) => {
                        return '<span class="center-block p-2 badge ' + this.getStatClass(data) + '">' + this.i18n.getTranslation(data) + '</span>';
                    }
                },
                {
                    data: 'vcfProcStatCd', className: 'text-center', defaultContent: '', render: (data) => {
                        return '<span class="center-block p-2 badge ' + this.getStatClass(data) + '">' + this.i18n.getTranslation(data) + '</span>';
                    }
                },
                {
                    data: 'useCd',
                    className: 'text-center',
                    defaultContent: '',
                    render: (data) => this.i18n.getTranslation(data)
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
                    render: (columnData: any, type: string, data: PtntInfo) => {
                        if (data.hpoProcStatCd === 'FST004' || data.mriProcStatCd === 'FST004' || data.vcfProcStatCd === 'FST004') {
                            return '<button type="button" class="btn btn-secondary btn-sm" action="disease"><i class="icon-append fa fa-history" action="disease"></i></button>';
                        } else {
                            return '';
                        }
                    }
                }
            ],
            rowCallback: (row: Node, data: PtntInfo, index: number) => {
                $('td', row).unbind('click');
                $('td', row).bind('click', (e) => {
                    const action = $(e.target).attr('action');
                    if ('disease' === action) {
                        this.diseaseModalComponent.show(data.ptntSeq);
                    } else {
                        this.router.navigate([`/patients/${data.ptntSeq}`]);
                    }
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

    public getStatClass(cd: string) {
        let str = 'badge-light'; // 	미등록
        if (cd === 'FST002') {// 	파일 등록
            str = 'badge-primary';
        } else if (cd === 'FST003') { // 	처리 중
            str = 'badge-warning';
        } else if (cd === 'FST004') { // 	완료
            str = 'badge-success';
        } else if (cd === 'FST005') { // 	실패
            str = 'badge-danger';
        }
        return str;
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
