import {
    AfterContentChecked,
    AfterContentInit,
    AfterViewChecked,
    Component,
    DoCheck,
    OnInit,
    ViewChild
} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AlertService, I18nService, JsonApiService, UserService} from '@app/services';
import {AuthDetail, BrdCateCd, CrudTypeCd} from '@generate/models';
import {Title} from '@angular/platform-browser';
import {BrdDatatableComponent, Where} from '@app/features/brds/datatable/brd.datatable.component';
import {BrdModalComponent} from '@app/features/brds/modal/brd-modal.component';
import {Page} from '@app/models/Page';

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-qa',
    templateUrl: './qa.component.html',
    styleUrls: ['./qa.component.css'],
    providers: []
})

export class QaComponent implements OnInit, DoCheck, AfterContentInit, AfterContentChecked, AfterViewChecked {
    public BrdCateCd = BrdCateCd;
    public where: Where = {search: ''};
    @ViewChild(BrdDatatableComponent) brd: BrdDatatableComponent;
    @ViewChild(BrdModalComponent) modal: BrdModalComponent;
    private auths: AuthDetail[] = [];

    constructor(private http: HttpClient, public router: Router,
                private jsonApiService: JsonApiService, private userService: UserService, private i18n: I18nService,
                private alerService: AlertService, private i18: I18nService, private title: Title) {
    }

    ngOnInit() {
        this.auths = [];
        this.userService.getAuths('/brds').subscribe(auths => {
            this.auths = auths;
        });

        $('input[name="checkSelectAll"]').on('click', function (e) {
            if (this.checked) {
                $('#bbsTable tbody input[type="checkbox"]:not(:checked)').trigger('click');
            } else {
                $('#bbsTable tbody input[type="checkbox"]:checked').trigger('click');
            }
            e.stopPropagation();
        });
    }

    ngDoCheck(): void {
        // console.log('ngDoCheck----');
    }

    ngAfterContentInit(): void {
        // console.log('ngAfterContentInit----');
    }

    ngAfterContentChecked(): void {
        // console.log('ngAfterContentChecked----');
    }

    ngAfterViewChecked(): void {
        // console.log('ngAfterViewChecked----');
    }

    // response($event: Page<Brd>) {
        // console.log('----->', $event);
    // }

    search() {
        this.brd.search();
    }

    register() {
        // this.modal.show({cateCd: 'BCC004'} as Brd);
    }

    isRegister() {
        return this.auths.filter(it => it.crudTypeCd === CrudTypeCd.POST).length > 0;
    }

    isSearch() {
        return this.auths.filter(it => it.crudTypeCd === CrudTypeCd.GET).length > 0;
    }

    registerComplete() {
        this.brd.search();
    }
}
