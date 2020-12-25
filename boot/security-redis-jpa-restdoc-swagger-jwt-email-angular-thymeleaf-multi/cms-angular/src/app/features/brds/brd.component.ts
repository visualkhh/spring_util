import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from '@app/services/ui/alert.service';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
@Component({
    selector: 'app-bbs',
    templateUrl: './brd.component.html',
    styleUrls: ['./brd.component.css']
})
export class BrdComponent implements OnInit {

    constructor(private alertService: AlertService) {
    }

    ngOnInit() {
    }

    popup() {
    }
}
