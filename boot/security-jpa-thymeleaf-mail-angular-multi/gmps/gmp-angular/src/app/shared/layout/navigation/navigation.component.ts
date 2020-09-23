import {Component, OnInit} from '@angular/core';
import {UserService} from '@web-core-app/services/user.service';
import {AuthDepthDetail} from '@web-core-generate/models';
import {AuthDetail, UseCd} from '@web-core-generate/models';
import {filter} from 'rxjs/operators';



@Component({
    selector: 'component-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

    public authDepthDetails: AuthDepthDetail[];
    constructor(public userService: UserService) {
    }

    ngOnInit() {
        this.userService.user$.pipe(filter(it => 'USE001' === it.useCd && it.authDupDepthDetails.length > 0)).subscribe(sit => {
            this.authDepthDetails = this.userService.authDupDepthDetailsLoopFilter((it) => 'GET' === it.crudTypeCd && 'USE002' === it.hddnCd);
            // console.log('this.authDepthDetails', this.authDepthDetails);
        });
    }

}
