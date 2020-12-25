import {Component, OnInit, ViewChild} from '@angular/core';
import {Adm, UseCd} from '@generate/models';
import {AlertService} from '@app/services/ui/alert.service';
import {UserService} from '@app/services/user.service';
import {I18nService} from '@app/services/i18n/i18n.service';
import {JsonApiService} from '@app/services/json-api.service';
import {AdmModalComponent} from '@app/features/setups/adms/modal/adm-modal.component';
import {AdmDatatableComponent} from '@app/features/setups/adms/datatable/adm.datatable.component';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {CodeService} from '@app/services/code/code.service';

@Component({
    selector: 'app-bbs',
    templateUrl: './adm.component.html',
    styleUrls: ['./adm.component.css']
})
export class AdmComponent implements OnInit {
    public UseCd = UseCd;
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
