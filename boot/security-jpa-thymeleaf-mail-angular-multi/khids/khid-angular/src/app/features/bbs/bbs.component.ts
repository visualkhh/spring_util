import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from '@web-core-app/services/ui/alert.service';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {WowDetailComponent} from '@web-core-app/features/modals/wowDetail/wow-detail.component';
@Component({
    selector: 'app-bbs',
    templateUrl: './bbs.component.html',
    styleUrls: ['./bbs.component.css']
})
export class BbsComponent implements OnInit {

    @ViewChild(WowDetailComponent) modal: WowDetailComponent;
    constructor(private alertService: AlertService) {
    }

    ngOnInit() {
    }

    popup() {
        this.modal.show({});
    }
}
