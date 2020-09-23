import {Component, OnInit, ViewChild} from '@angular/core';
import {PolicyInfo, AuthDetail, CorpGrp} from '@web-core-generate/models';
import {Page} from '@web-core-app/models/Page';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {UserService} from '@web-core/app/services/user.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {ApiHttpOption, JsonApiService} from '@web-core/app/services/json-api.service';
import {AdmModalComponent} from '@web-core-app/features/setups/adms/modal/adm-modal.component';
import {CorpDatatableComponent} from '@web-core-app/features/setups/corps/datatable/corp.datatable.component';
import {CorpModalComponent} from '@web-core-app/features/setups/corps/modal/corp-modal.component';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Adm} from '@web-core-generate/models';
import {CodeService} from '@web-core-app/services/code/code.service';
@Component({
    selector: 'app-bbs',
    templateUrl: './corp.component.html',
    styleUrls: ['./corp.component.css']
})
export class CorpComponent implements OnInit {

    private auths: AuthDetail[] = [];
    @ViewChild(CorpDatatableComponent) table: CorpDatatableComponent;
    @ViewChild(CorpModalComponent) modal: CorpModalComponent;

    constructor(private http: HttpClient, public router: Router, private codeService: CodeService,
                private api: JsonApiService, private userService: UserService, private i18n: I18nService,
                private alerService: AlertService, private i18: I18nService, private title: Title) {
    }

    /*
        POL001,//	개인정보취급방침
        POL002,//	이용약관
     */
    where: CorpGrp;
    ngOnInit() {
        this.resetWhere();
        this.auths = [];
        this.userService.getAuths('/setups/corps').subscribe(auths => {
            this.auths = auths;
        });

    }

    resetWhere() {
        this.where = {} as CorpGrp | any;
    }

    search() {
        this.table.search();
    }

    admComplete($event: any) {
        this.search();
    }
    isRegister() {
        return this.auths.filter(it => it.crudTypeCd === 'POST').length > 0;
    }
    isSearch() {
        return this.auths.filter(it => it.crudTypeCd === 'GET').length > 0;
    }
    register() {
        this.modal.show();
    }

    response($event: Page<CorpGrp>) {
    }

    registerComplete() {
        this.search();
    }
}
