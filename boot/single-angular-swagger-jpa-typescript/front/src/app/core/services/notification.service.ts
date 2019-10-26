import {Injectable} from '@angular/core';
import {I18nService} from "@app/shared/i18n/i18n.service";

declare var $: any;
// const Loading = require('external-lib/Customizable-Loading-Modal-Plugin/js/modal-loading.js');

@Injectable()
export class NotificationService {
    // private progress: any;

    constructor() {
    }

    smallBox(data, cb?) {
        $.smallBox(data, cb)
    }

    bigBox(data, cb?) {
        $.bigBox(data, cb)
    }

    smartMessageBox(data, cb?) {
        $.SmartMessageBox(data, cb)
    }

    primaryAlert(title: string, content: string) {
        this.smallBox({
            title: title,
            content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
            color: "#3182c4",
            iconSmall: "fa fa-times fa-2x fadeInRight animated",
            timeout: 4000
        });
    }
    infoAlert(title: string, content: string) {
        this.smallBox({
            title: title,
            content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
            color: "#3b9285",
            iconSmall: "fa fa-times fa-2x fadeInRight animated",
            timeout: 4000
        });
    }

    successAlert(title: string, content: string) {
        this.smallBox({
            // title: title,
            title: '알림',//this.i18n.getTranslation('Notify'),
            content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
            color: "#56a238",
            iconSmall: "fa fa-times fa-2x fadeInRight animated",
            timeout: 4000
        });
    }

    dangerAlert(title: string, content: string) {
        this.smallBox({
            // title: title,
            title: '알림',//this.i18n.getTranslation('Notify'),
            content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
            color: "#C46A69",
            iconSmall: "fa fa-times fa-2x fadeInRight animated",
            timeout: 4000
        });
    }
    progressSpinnerOpen(){
        return  new $.Loading({
            direction: 'hor',
            discription: 'Loading...',
            // animationIn: true,
            // animationOut: true,
            defaultApply: 	true,
            maskBgColor:  "rgba(0,0,0,0.1)"
        });
        // return  new $.Loading();
    }
    // progressSpinnerClose(){
    //     this.progress.out();
    // }
}
