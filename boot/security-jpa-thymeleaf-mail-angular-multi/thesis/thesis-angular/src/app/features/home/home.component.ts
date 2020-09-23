import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from "@web-core-app/services/ui/alert.service";
import { WebCoreDatepickerDirective } from '@web-core-app/directives/form/input/webCoreDatepicker.directive';
import * as moment from 'moment'
import {HomeListComponent} from "@app/features/home/modal/home-list.component";
@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    @ViewChild(HomeListComponent) modal: HomeListComponent;

    @ViewChild('startdate', {read: WebCoreDatepickerDirective})
    startDatePicker: WebCoreDatepickerDirective;

  constructor(private alertService: AlertService) {
      let loading = alertService.progressSpinnerOpen();
      setTimeout(() =>{loading.out();} , 1000)
      setTimeout(() =>{ alertService.dangerAlert('title','errrrr');} , 1000);
      setTimeout(() =>{ alertService.successAlert('title go','good');} , 2000);
  }

  ngOnInit() {
  }

    selectStartDate(data: Date | any | Event) {
      console.log('--->',data);
        console.log(moment(data).format("YYYY__MM__DD"));
        // if (data instanceof Event) {
        //     if (this.validationService.isDateFormat(data.target['value'])) {
        //         data = moment(data.target['value'], 'YYYY.MM.DD').toDate();
        //         this.startDatePicker.setDate(data);
        //     } else {
        //         data.target['value'] = '';
        //         data = null;
        //     }
        // }
        // this.where.ptcpStDt = this.cmsCommonService.getMondayOfWeek(data); //data
    }

    popup() {
        this.modal.show({});
    }

    modalComplete($event: any) {
        alert('zzzzzz');
    }
}
