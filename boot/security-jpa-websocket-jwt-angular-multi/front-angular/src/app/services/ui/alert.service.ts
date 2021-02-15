import {Injectable} from '@angular/core';
// import {I18nService} from "@app/shared/i18n/i18n.service";
// import '../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading.css';
// import '../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading-animate.css';

// import $ from 'jquery';
// import 'jquery';
declare var $: any;

import '@libs/bootstrap-toaster/dist/js/bootstrap-toaster.js';
import '@libs/Customizable-Loading-Modal-Plugin/js/modal-loading.js';
import {HttpErrorResponse} from '@angular/common/http';
import {MsgCode} from '@app/shareds/code/MsgCode';
// require('../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading.css');
// require('../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading-animate.css');
// const Loading = require('external-lib/Customizable-Loading-Modal-Plugin/js/modal-loading.js');
@Injectable()
export class AlertService {
    // private progress: any;

    constructor() {
    }

    // smallBox(data, cb?) {
    //     $.smallBox(data, cb)
    // }
    //
    // bigBox(data, cb?) {
    //     $.bigBox(data, cb)
    // }
    //
    // smartMessageBox(data, cb?) {
    //     $.SmartMessageBox(data, cb)
    // }
    //
    // primaryAlert(title: string, content: string) {
    //     this.smallBox({
    //         title: title,
    //         content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
    //         color: "#3182c4",
    //         iconSmall: "fa fa-times fa-2x fadeInRight animated",
    //         timeout: 4000
    //     });
    // }
    // infoAlert(title: string, content: string) {
    //     this.smallBox({
    //         title: title,
    //         content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
    //         color: "#3b9285",
    //         iconSmall: "fa fa-times fa-2x fadeInRight animated",
    //         timeout: 4000
    //     });
    // }

    successAlert(title: string, content: string) {
      return new $.toaster({
            content,
            title,
            delay: 3000,
            containerOption: {
                style: 'background-color:#55aa55ee',
            },
            // buttonOption: {
            //     style: 'color: yellow',
            // },
            headerOption: {
                style: 'background-color:#55aa55ee; color:#ffffff',
                //     class: 'wow'
            },
            bodyOption: {
                style: 'background-color:#55aa55ee; color:#ffffff',
                class: 'wow'
            }
        });
    }
    secondaryAlert(title: string, content: string) {
      return new $.toaster({
            content,
            title,
            delay: 3000,
            containerOption: {
                style: 'background-color:#4b5258ee',
            },
            // buttonOption: {
            //     style: 'color: yellow',
            // },
            headerOption: {
                style: 'background-color:#6c757dee; color:#ffffff',
                //     class: 'wow'
            },
            bodyOption: {
                style: 'background-color:#6c757dee; color:#ffffff',
                class: 'wow'
            }
        });
    }

    dangerAlertHttpErrorResponse(title: string, error: HttpErrorResponse | Error | string) {
        if (typeof error === 'string') {
            this.dangerAlert(`${title}`, error);
        } else {
            // const code = error.code || 'E99999';
            let message = '';
            if (error instanceof HttpErrorResponse) {
                message = error?.error?.message || (error.message || MsgCode.E99999);
            } else {
                message = (error.message || MsgCode.E99999) as string;
            }
            this.dangerAlert(`${title}`, message);
        }
            // this.alertService.dangerAlert(title + '(' + code + ')', message);
    }
    dangerAlert(title: string, content: string) {
        return new $.toaster({
            content,
            title,
            delay: 3000,
            containerOption: {
                style: 'background-color:#dd5555ee',
            },
            // buttonOption: {
            //     style: 'color: yellow',
            // },
            headerOption: {
                style: 'background-color:#dd5555ee; color:#ffffff',
                //     class: 'wow'
            },
            bodyOption: {
                style: 'background-color:#dd5555ee; color:#ffffff',
                class: 'wow'
            }
        });
    }
    progressSpinnerOpen(url = ''): {out: () => void}{
        return  new $.Loading({
            direction: 'hor',
            discription: url + ' Loading...',
            // animationIn: true,
            // animationOut: true,
            defaultApply: 	true,
            maskBgColor:  'rgba(0,0,0,0.1)'
        });
        // pro.out();
        // return  new $.Loading();
    }
    progressSpinnerAllOut() {

        $.Loading.allOut();
    }
    // progressSpinnerClose(){
    //     this.progress.out();
    // }
}
