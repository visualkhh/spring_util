import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {BrdModalComponent} from "../brd-modal/brd-modal.component";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-notice',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css'],
    providers: [CmsCommonService]
})

export class BoardComponent implements OnInit {

    @ViewChild(BrdModalComponent) brdModal: BrdModalComponent;
    @ViewChild(DatatableComponent) table: DatatableComponent;
    public where = {
        search : ""
    }
    private cateCd: BrdCateCd;
    
    constructor(private route: ActivatedRoute, private http: HttpClient, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private i18: I18nService) {
    }

    ngOnInit() {
        this.cateCd = this.route.snapshot.data.cateCd;

        // this.title.setTitle('공지사항');
        // this.userService.user$.subscribe(user => {
        //     this.userDetails = user;
        // });
        // this.userDetails.isAccept(this.router.url, CRUD.R, CRUD.D);
        this.brdModal.cateCd = this.cateCd;

        $('input[name="checkSelectAll"]').on('click', function (e) {
            if (this.checked) {
                $('#bbsTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
            } else {
                $('#bbsTable tbody input[type="checkbox"]:checked').trigger('click');
            }
            e.stopPropagation();
        });
    }

    options = {
        serverSide: true,
        paging: true,
        ordering: false,
        lengthMenu: [[50, 100, 150, -1], [50, 100, 150, "All"]],
        ajax: (data, callback, setting) => {
            let where = {"size": data.length, "page": data.start / data.length, "draw": data.draw} as DataTablePageRequest;
            where['search'] = this.where.search;
            const action = new ApiAction<DataTablePageResponse<any>>(this.router.url);
            action.param = where;
            action.success = callback;
            this.jsonApiService.get(action);
        },
        columns: [
            {data: 'brdSeq', className: 'text-center col-md-1', defaultContent: ""},
            {data: 'titl', className: 'text-center ', defaultContent: ""},
            {
                data: 'useCd', className: 'text-center col-md-1', defaultContent: "", visible: false,
                render: (data, display, row) => {
                    if (data == 'USE001')
                        var str = 'label-success';
                    else
                        var str = 'label-danger';
                    return '<span class="center-block padding-5 label ' + str + '">' + I18nService.getInstance().getTranslation(data) + '</span>';
                }
            },
            {data: 'regDt', className: 'text-center col-md-2', defaultContent: "", render: (date) => moment(date).format('YYYY.MM.DD')}
        ],
        rowCallback: (row: Node, data: any[] | Object, index: number) => {
            $('td', row).unbind('click');
            $('td', row).bind('click', (e) => {
                let actionStr: string = $(e.target).attr("action");
                this.brdModal.show(Object.assign({}, data) as Brd);
            });
            return row;
        }
    };


    private showRegDialog() {
        this.brdModal.show();
    }

    //
    // private noticeDelete() {
    //     let deleteArr: Array<string> = new Array<string>();
    //     $.each($('#bbsTable tbody input[type="checkbox"]:checked'), (key, value) => {
    //         deleteArr.push($(value).val());
    //     });
    //     if (deleteArr.length > 0) {
    //         /*this.http.post(`${this.router.url}/delete`, {"deleteParam": deleteArr})
    //             .catch(this.bbsModal.handleError)
    //             .subscribe((data: any) => {
    //                 $("#bbsTable table").DataTable().ajax.reload();
    //             });*/
    //     }
    // }

    public search() {
        this.table.draw();
        // console.log(this.where.search);
    }

    add() {
    }

    modalComplete(wow: any) {
        this.where.search = "";
        this.table.draw();
    }
}
