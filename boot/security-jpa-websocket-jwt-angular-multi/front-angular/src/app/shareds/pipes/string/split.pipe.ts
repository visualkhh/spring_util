import {Pipe, PipeTransform} from '@angular/core';
import {ValidUtil} from '@app/utils/ValidUtil';

@Pipe({
    name: 'split',
    pure: false
})
export class SplitPipe implements PipeTransform {

    constructor() {
    }

    transform(phrase: string, sp: string, arr: string | number = 'first'): any {
        if (phrase && sp) {
            const a = phrase.split(sp);
            if (arr === 'first') {
                return a[0];
            } else if (arr === 'end') {
                return a[a.length - 1];
            } else if (ValidUtil.isNumber(arr)) {
                return a[a.length + Number(arr)];
            }
        } else {
            return phrase;
        }
    }
}
