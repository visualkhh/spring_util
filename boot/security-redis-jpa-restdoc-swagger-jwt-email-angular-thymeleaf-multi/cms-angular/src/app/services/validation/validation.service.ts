import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';




import {Output, EventEmitter} from '@angular/core';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {I18nService} from '@app/services/i18n/i18n.service';
import {AlertService} from '@app/services/ui/alert.service';

declare let $: any;
declare let moment: any;

@Injectable()
export class ValidationService {

    constructor(private http: HttpClient, private router: Router, private alertService: AlertService, private i18n: I18nService) {
    }

    public validCheck(thisForm: FormGroup) {
        $.each(thisForm.controls, (key, value) => {
            if (!value.valid) {
                $('[name=' + key + ']').focus();
                const pla = $('[name=' + key + ']').attr('placeholder');
                this.alertService.dangerAlert((pla || '') + ' ' + this.i18n.getTranslation('Input'), (pla || '') + ' ' + this.i18n.getTranslation('Valid_' + Object.keys(value.errors)[0]));
            }
        });
    }
    isDateFormat(date: string): boolean {
        if (date && (typeof date === 'string') && (date.match(/^(\d{4})\.(\d{2})\.(\d{2})$/) || date.match(/^(\d{8})$/))) {
            return true;
        }
        return false;
    }

}
