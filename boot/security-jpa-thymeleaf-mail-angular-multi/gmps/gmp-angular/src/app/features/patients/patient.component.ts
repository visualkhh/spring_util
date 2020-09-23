import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {I18nService} from '@web-core-app/services/i18n/i18n.service';
import {CodeService} from '@web-core-app/services/code/code.service';
import {JsonApiService} from '@web-core-app/services/json-api.service';
import {Title} from '@angular/platform-browser';
import {GenderCd, ProcStatCd, PtntInfo} from '@web-core-generate/models';
import {Page} from '@web-core-app/models/Page';
import {PageRequest} from '@web-core-app/models/PageRequest';
import {DatatableComponent} from '@web-core-app/features/datatable/datatable.component';
import {MomentPipe} from '@web-core-app/pipes/date/moment.pipe';
import {MomentService} from '@web-core-app/services/date/moment.service';
import {PatientDatatableComponent, Where} from '@web-core-app/features/patients/datatable/patient.datatable.component';
import {DiseaseModalComponent} from '@web-core-app/features/patients/modal/disease/disease-modal.component';

declare let $: any;


@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.component.css'],
    providers: []
})
export class PatientComponent implements OnInit, OnDestroy {

    @ViewChild(PatientDatatableComponent) table: PatientDatatableComponent;
    @ViewChild(DiseaseModalComponent) diseaseModalComponent: DiseaseModalComponent;
    public where: Where;

    constructor(private http: HttpClient, private router: Router, private codeService: CodeService,
                private i18n: I18nService, private api: JsonApiService, private title: Title, private momentService: MomentService) {
    }


    resetWhere() {
        this.where = {
            // name: '', fromAge: '', toAge: '',
            gen: '',
            hpoProcStatCd: '',
            mriProcStatCd: '',
            vcfProcStatCd: '',
        } as Where;
    }

    ngOnInit() {
        this.resetWhere();
    }

    ngOnDestroy() {
    }

    search() {
        this.table.search();
    }
}
