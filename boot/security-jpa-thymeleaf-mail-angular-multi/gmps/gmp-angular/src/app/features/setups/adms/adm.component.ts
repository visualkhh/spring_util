import {Component, OnInit, ViewChild} from '@angular/core';
import {PolicyInfo} from '@web-core-generate/models';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {UserService} from '@web-core/app/services/user.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {ApiHttpOption, JsonApiService} from '@web-core/app/services/json-api.service';
import {AdmModalComponent} from '@web-core-app/features/setups/adms/modal/adm-modal.component';
import {AdmDatatableComponent} from '@web-core-app/features/setups/adms/datatable/adm.datatable.component';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Adm} from '@web-core-generate/models';
import {CodeService} from '@web-core-app/services/code/code.service';
@Component({
    selector: 'app-bbs',
    templateUrl: './adm.component.html',
    styleUrls: ['./adm.component.css']
})
export class AdmComponent implements OnInit {

    where: Adm;
    @ViewChild(AdmDatatableComponent) table: AdmDatatableComponent;
    @ViewChild(AdmModalComponent) admModal: AdmModalComponent;

    constructor(private http: HttpClient, public router: Router, private codeService: CodeService,
                private api: JsonApiService, private userService: UserService, private i18n: I18nService,
                private alerService: AlertService, private i18: I18nService, private title: Title) {
    }
    ngOnInit() {
        this.resetWhere();
    }

    resetWhere() {
        this.where = {useCd: ''} as Adm | any;
    }

    search() {
        this.table.search();
    }

    showAdmModal() {
        this.admModal.show();
    }

    admComplete($event: any) {
        this.search();
    }
}
