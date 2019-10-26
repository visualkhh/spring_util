import {ApplicationRef, Pipe, PipeTransform} from '@angular/core';
import {I18nService} from "./i18n.service";
import {JsonApiService} from "@app/core/services";

@Pipe({
    name: 'i18n',
    pure: false
})
export class I18nPipe implements PipeTransform {

    constructor(private jsonApiService: JsonApiService, private ref: ApplicationRef) {
        // console.log("I18nPipe cocococo")
    }

    transform(phrase: any, ...agument: any[]): any {
        if (phrase) {
            return I18nService.getInstance(this.jsonApiService, this.ref).getTranslation(phrase, agument);
        }
    }

}
