import {Component, OnInit} from '@angular/core';
import {UserService} from '@app/services/user.service';
import {AuthDepthDetail} from '@generate/models';
import {AuthDetail, UseCd, CrudTypeCd} from '@generate/models';
import {filter} from 'rxjs/operators';

declare let $: any;
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
        this.userService.user$.pipe(filter(it => UseCd.USE001 === it.useCd && it.authDupDepthDetails.length > 0)).subscribe(sit => {
            this.authDepthDetails = this.userService.authDupDepthDetailsLoopFilter((it) => CrudTypeCd.GET === it.crudTypeCd && UseCd.USE002 === it.hddnCd);
            // console.log('this.authDepthDetails', this.authDepthDetails);
        });
    }

    // https://getbootstrap.com/docs/4.0/components/collapse/
    closeCollapse() {
        $('.collapse').collapse('hide');
    }
}
