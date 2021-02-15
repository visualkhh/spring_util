import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';
import {MomentService} from '@app/services/date/moment.service';

@Pipe({
  name: 'moment'
})
export class MomentPipe implements PipeTransform {

    constructor(private momentService: MomentService) {
      // console.log('moment ipe'+i18)
    }

  //   transform(value: any, format?: any): any {
  //       return this.i18.dateFormat(value, format);
  // }
    transform(date: string | Date, parameter = 'YYYY-MM-DD HH:mm:ss'): any {
        // return this.i18.dateFormat(value, format);
        return this.momentService.moment(date).format(parameter);
    }

}
