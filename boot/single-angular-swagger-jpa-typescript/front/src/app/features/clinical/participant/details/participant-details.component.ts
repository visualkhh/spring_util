import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {ApiAction, CmsCommonService, JsonApiService, NotificationService, UserService} from "@app/core/services";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {Title} from "@angular/platform-browser";
import {MomentPipe} from "@app/shared/pipes/moment.pipe";
import {BrdCateCd, DataTablePageRequest, DataTablePageResponse, User, UserDetails} from "@app/model/commomModels";
import {Brd} from "@app/model/commomModels";
import {DatatableComponent} from "@app/shared/ui/datatable/datatable.component";
import {ParticipantModalComponent} from "@app/features/clinical/participant/modal/participant-modal.component";

declare let $: any;
declare let moment: any;

@Component({
    selector: 'app-notice',
    templateUrl: './participant-details.component.html',
    styleUrls: ['./participant-details.component.css'],
    providers: [CmsCommonService]
})

export class ParticipantDetailsComponent implements OnInit {
    @ViewChild(ParticipantModalComponent) modal: ParticipantModalComponent;
    public tabState = 0;
    private user: User;

    constructor(private route: ActivatedRoute, private momentPipe: MomentPipe, private http: HttpClient, public router: Router, private jsonApiService: JsonApiService, public userService: UserService, private i18: I18nService) {
    }

    ngOnInit() {
        this.getUser();
    }


    public getUser() {
        const action = new ApiAction<User>(this.router.url);
        action.success = (data => {
            data.ptcpStDt = moment(data.ptcpStDt).toDate();
            data.ptcpEndDt = moment(data.ptcpEndDt).toDate();
            this.user = data;
        });
        this.jsonApiService.get(action);
    }
}
