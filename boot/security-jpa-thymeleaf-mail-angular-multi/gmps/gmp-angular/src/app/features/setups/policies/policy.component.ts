import {Component, OnInit, ViewChild} from '@angular/core';
import {PolicyInfo} from '@web-core-generate/models';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {UserService} from '@web-core/app/services/user.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {ApiHttpOption, JsonApiService} from '@web-core/app/services/json-api.service';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {ContentChange, QuillEditorComponent} from 'ngx-quill';
@Component({
    selector: 'app-bbs',
    templateUrl: './policy.component.html',
    styleUrls: ['./policy.component.css']
})
export class PolicyComponent implements OnInit {

    policy: PolicyInfo = {plcyCont: ''} as PolicyInfo;
    terms: PolicyInfo = {plcyCont: ''} as PolicyInfo;
    // @ViewChild('policy', {read: QuillEditorComponent}) policy: QuillEditorComponent;
    // @ViewChild('terms', {read: QuillEditorComponent}) terms: QuillEditorComponent;
    constructor(private http: HttpClient, public router: Router,
                private api: JsonApiService, private userService: UserService, private i18n: I18nService,
                private alerService: AlertService, private i18: I18nService, private title: Title) {
    }

    /*
        POL001,//	개인정보취급방침
        POL002,//	이용약관
     */
    ngOnInit() {
        this.policy = {plcyCont: ''} as PolicyInfo;
        this.terms = {plcyCont: ''} as PolicyInfo;
        this.api.get<PolicyInfo[]>('/setups/policies').subscribe(it => {
            this.policy = it.find(sit => 'POL001' === sit.plcyCd) || this.policy;
            this.terms = it.find(sit => 'POL002' === sit.plcyCd) || this.terms;
        }, this.api.errorHandler.bind(this.api));
    }

    policySave() {
        this.policy.plcyCd = 'POL001';
        this.api.post<PolicyInfo>('/setups/policies', {params: this.policy}).subscribe(it => {
            this.policy = it;
            this.alerService.successAlert(this.i18n.getTranslation(this.policy.plcyCd), this.i18n.getTranslation('msg.success'));
        }, this.api.errorHandler.bind(this.api));
    }

    termsSave() {
        this.terms.plcyCd = 'POL002';
        this.api.post<PolicyInfo>('/setups/policies', {params: this.terms}).subscribe(it => {
            this.terms = it;
            this.alerService.successAlert(this.i18n.getTranslation(this.terms.plcyCd), this.i18n.getTranslation('msg.success'));
        }, this.api.errorHandler.bind(this.api));
    }

    handleContentChange($event: ContentChange) {

    }
}
