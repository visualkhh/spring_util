import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {AlertService} from '@app/services/ui/alert.service';

declare let $: any;
declare let moment: any;

@Injectable()
export class ValidationService {

    constructor(private http: HttpClient, private router: Router, private alertService: AlertService) {
    }

    public validCheck(thisForm: FormGroup, prefix: string = '') {
        $.each(thisForm.controls, (key, value) => {
            if (!value.valid) {
                if (value instanceof  FormGroup && value.controls) {
                    this.validCheck(value, prefix + key);
                }
                $('[name=' + prefix + key + ']').focus();
                const pla = $('[name=' + prefix + key + ']').attr('placeholder');
                if (pla) {
                this.alertService.dangerAlert((pla || '') + ' ' + '입력', (pla || '') + ' ' + '확인해주세요');
                }
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
