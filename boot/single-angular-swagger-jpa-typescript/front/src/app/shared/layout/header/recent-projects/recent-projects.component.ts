import {Component, OnInit, TemplateRef} from '@angular/core';
import {RecentProjectsService} from "./recent-projects.service";
import {ApiAction, JsonApiService, UserService} from "@app/core/services";
import {BsModalService} from "ngx-bootstrap/modal";
import {BsModalRef} from "ngx-bootstrap/modal/bs-modal-ref.service";
import {Adm} from "@app/model/commomModels";

@Component({
    selector: 'sa-recent-projects',
    templateUrl: './recent-projects.component.html',
    providers: [RecentProjectsService]
})
export class RecentProjectsComponent implements OnInit {

    projects: Array<any>;
    bsModalRef: BsModalRef;

    constructor(private projectsService: RecentProjectsService, private api: JsonApiService, public userService: UserService, private modalService: BsModalService) {

    }

    ngOnInit() {
        this.userService.isAceept()
    }


    openModal(event, template: TemplateRef<any>) {
        event.preventDefault();
        this.bsModalRef = this.modalService.show(template);
    }

    onAgree(data: Adm | any) {
        const action = new ApiAction('/auth/detail');
        action.param = data;
        action.success = (data) => {
            $("#appInfoTable table").DataTable().ajax.reload();
            // this.user.reloadUserDetails();
            this.bsModalRef.hide()
            this.api.httpClientHandleSuccess();
        };
        this.api.put(action);
    }

    onClose(event: any) {
        this.bsModalRef.hide()
    }

    // clearProjects(){
    //   this.projectsService.clearProjects();
    //   this.projects = this.projectsService.getProjects()
    //
    // }

}
