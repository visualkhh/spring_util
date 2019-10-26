import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment'

@Pipe({
  name: 'moment'
})
export class MomentPipe implements PipeTransform {

    constructor() {
      // console.log('moment ipe'+i18)
    }

  //   transform(value: any, format?: any): any {
  //       return this.i18.dateFormat(value, format);
  // }
    transform(date: string|Date, parameter = 'YYYY-MM-DD HH:mm:ss'): any {
        // return this.i18.dateFormat(value, format);
        return moment(date).format(parameter);
    }

}
