import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'khh'
})
export class KhhPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    debugger;
    return value+"_1111";
  }

}
