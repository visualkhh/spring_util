import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

import {config} from '@app/core/smartadmin.config';
import {Observable} from "rxjs";
import {delay, map, catchError} from 'rxjs/operators';



import {Output, EventEmitter} from '@angular/core';
import {Router} from "@angular/router";
import {FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {I18nService} from "@app/shared/i18n/i18n.service";
import {NotificationService} from "@app/core/services/notification.service";

declare let $:any;
declare let moment:any;

@Injectable()
export class ValidationService {
    // public static DATE_FORMAT = 'YYYY.MM.DD';

    constructor(private http: HttpClient, private router: Router, private notificationService: NotificationService) {
    }

    isDateFormat(date: string): boolean {
        if (date && date.match(/^(\d{4})\.(\d{2})\.(\d{2})$/)) {
            return true;
        }
        return false;
    }

    
    getEmailValidatorFn(): ValidatorFn {
        return Validators.pattern(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
        // return Validators.pattern(/^(\d{4})\.(\d{2})\.(\d{2})$/);
    }
    getPhoneValidatorFn(): ValidatorFn {
        return Validators.pattern(new RegExp('^\\d+$'));
        // return Validators.pattern(/^(\d{4})\.(\d{2})\.(\d{2})$/);
    }

    
    public validCheck(thisForm: FormGroup) {
        console.log(thisForm);
        $.each(thisForm.controls, (key, value) => {
            if (!value.valid) {
                // let tar = $("[name=" + key + "]");
                // tar = tar.length > 1 ? $(tar[tar.length-1]) : tar;
                // tar = tar[tar.length-1];
                var tar = $("[name=" + key + "]").last();
                tar.focus();
                const pla = tar.attr('placeholder')
                this.notificationService.dangerAlert((pla||'') + ' 입력', (pla||'') +' '+I18nService.getInstance().getTranslation("Valid_" + Object.keys(value.errors)[0]));
            }
        });
    }
}
