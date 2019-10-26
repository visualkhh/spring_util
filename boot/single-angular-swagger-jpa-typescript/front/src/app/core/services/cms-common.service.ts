import {Injectable} from '@angular/core';
import {NotificationService} from "@app/core/services/notification.service";
import {Moment, UnitOfTime} from "smartadmin-plugins/bower_components/moment/moment";

declare let $:any;
declare let moment:any;
// import * as moment from 'moment'
@Injectable()
export class CmsCommonService {
    // @Output() confirmOk = new EventEmitter();
    // @Output() confirmCancel = new EventEmitter();
    //
    // commercialUrl: string = "/conts/commercial";
    //
    constructor(private notificationService: NotificationService) {
    }

    /*
    type UnitOfTime = ("year" | "years" | "y" |
              "quarter" | "quarters" | "Q" |
              "month" | "months" | "M" |
              "week" | "weeks" | "w" |
              "day" | "days" | "d" |
              "hour" | "hours" | "h" |
              "minute" | "minutes" | "m" |
              "second" | "seconds" | "s" |
              "millisecond" | "milliseconds" | "ms");
     */
    public getMondayOfWeek(date = new Date()) {
        // date = moment('2019.10.03','YYYY.MM.DD');
        //https://momentjs.com/docs/#/get-set/weekday/
        return moment(date).isoWeekday('Monday').toDate();
        // return moment(date).subtract(moment(date).day() - 1, 'day').toDate();
    }
    public  getSundayOfWeek(date = new Date()) {
        // (moment(date) as Moment).add()
        // return moment(date).add(6 - moment(date).day() + 1, 'day').toDate();
        return moment(date).isoWeekday('Sunday').toDate();
    }

    //https://momentjs.com/docs/
    //unit: days, years, months, seconds, millisecond
    betweenDates(from: Date, to: Date, unit: UnitOfTime, unitValue: number) {
        let fromM = moment(from); // as Moment;
        const toM = moment(to); // as Moment;
        //fromM.milliseconds()
        const dates = new Array<Date>();

        while(fromM.valueOf() <= toM.valueOf()) {
            dates.push(fromM.toDate());
            fromM = fromM.add(unit, unitValue);
        }
        return dates;
    }

    // betweenDates(from: Date, to: Date, unit: UnitOfTime, unitValue: number) {
    //     moment().subtract(1, 'week');
    // }


    //
    // public dateConvert(date: any) {
    //     if (date) return moment(new Date(date)).format("YYYY-MM-DD HH:mm:ss");
    // }
    //
    // public validCheck(thisForm: FormGroup) {
    //     $.each(thisForm.controls, (key, value) => {
    //         if (!value.valid) {
    //             $("[name=" + key + "]").focus();
    //             this.errorBox(I18nService.getTranslation("Valid_" + Object.keys(value.errors)[0]));
    //         }
    //     });
    // }
    //
    // public handleError(error: any) {
    //     let errMsg = (error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` : I18nService.getTranslation("msg.error.server");
    //     console.error(errMsg); // log to console instead
    //     return Observable.throw(errMsg);
    // }
    //
    // public successBox(msg: string) {
    //     this.notificationService.smallBox({
    //         title: I18nService.getTranslation("word.success"),
    //         content: (msg || I18nService.getTranslation("msg.success")),
    //         color: "#89AD45",
    //         timeout: 3000,
    //         icon: "fa fa-exclamation"
    //     });
    // }
    //
    // public errorBox(msg: string) {
    //     this.notificationService.smallBox({
    //         title: I18nService.getTranslation("word.error"),
    //         content: (msg || I18nService.getTranslation("msg.error")),
    //         color: "#FF0000",
    //         timeout: 3000,
    //         icon: "fa fa-exclamation"
    //     });
    // }
    //
    // public smartMsgBox(msgTitl: string, msg: string, btn: string, callBack: any) {
    //     msgTitl = (msgTitl || I18nService.getTranslation("word.delete"));
    //     msg = (msg || I18nService.getTranslation("msg.delete"));
    //     btn = (btn || "["+I18nService.getTranslation("word.ok")+"]["+I18nService.getTranslation("word.cancel")+"]");
    //
    //     this.notificationService.smartMessageBox({
    //         title: msgTitl,
    //         content: msg,
    //         buttons: btn
    //     }, callBack);
    // }
    //
    // public onFileChange(event, id, formGroup, fileType) {
    //     let reader = new FileReader();
    //     if(event.target.files && event.target.files.length > 0) {
    //         let file = event.target.files[0];
    //         let fileTypeCd:String = file.type.split("/")[0];
    //         fileTypeCd = (fileTypeCd == "image")? "FIL001" : ((fileTypeCd == "audio")? "FIL002" : ((fileTypeCd == "video")? "FIL003" : ((file.type.indexOf("zip") > -1)? "FIL004" : "")));
    //         if (file.size > environment.maxFileSize) {
    //             this.errorBox(I18nService.getTranslation("msg.error.fileSize"));
    //             return false;
    //         }
    //         if (fileTypeCd && fileTypeCd == fileType) {
    //             console.log("tk_console_test =====> ", formGroup.get(id));
    //             /*reader.readAsDataURL(file);
    //             reader.onload = () => {
    //                 formGroup.get(id).setValue({
    //                     fileNm: file.name,
    //                     fileTypeCd: fileTypeCd,
    //                     fileSize: file.size,
    //                     value: reader.result.split(',')[1]
    //                 })
    //             };*/
    //         } else if(fileTypeCd != fileType) {
    //             $("#"+id).val("");
    //             this.errorBox(I18nService.getTranslation("Not" + fileType));
    //         }
    //     }
    // }
    //
    // public commercial(cacheId, type = "") {
    //     const param = (type)? {"cacheId" : cacheId, "type" : type} : {"cacheId" : cacheId};
    //     this.smartMsgBox(I18nService.getTranslation("Commercial"),
    //         I18nService.getTranslation("CommercialMsg"),
    //         "",
    //         (ButtonPressed) => {
    //             if (ButtonPressed == I18nService.getTranslation("word.ok")) {
    //                 this.http.post(this.commercialUrl, param)
    //                     .catch(this.handleError)
    //                     .subscribe((data: any) => {
    //                         this.successBox("");
    //                     })
    //             }
    //         });
    // }

    /* Data table Language */
    /*public datatableLanguage() {
        return {
            processing: "Procesando...",
            search: "Buscar:",
            lengthMenu: "Mostrar _MENU_ &eacute;l&eacute;ments",
            info: "Mostrando desde _START_ al _END_ de _TOTAL_ elementos",
            infoEmpty: "Mostrando ningún elemento.",
            infoFiltered: "(filtrado _MAX_ elementos total)",
            infoPostFix: "",
            loadingRecords: "Cargando registros...",
            zeroRecords: "No se encontraron registros",
            emptyTable: "No hay datos disponibles en la tabla",
            paginate: {
                first: "Primero",
                previous: "Anterior",
                next: "Siguiente",
                last: "Último"
            },
            aria: {
                sortAscending: ": Activar para ordenar la tabla en orden ascendente",
                sortDescending: ": Activar para ordenar la tabla en orden descendente"
            }
        }
    }*/

    // contsDatatableOption: any = {
    //     serverSide: true,
    //     paging: true,
    //     ordering: false,
    //     rowReorder: {
    //         dataSrc: 'playlistOrd',
    //         selector: 'td:nth-child(2)',
    //         update: false
    //     },
    //     info: true,
    //     ajax: (data, callback, setting) => {
    //         let form = {};
    //         form["search"] = $("[name=searchWord]").val();
    //         let info = {"start": data.start, "size": data.length, "page": data.start / data.length, "draw": data.draw};
    //         $.extend(form, info);
    //
    //         this.http.get(this.router.url + "?" + $.param(form))
    //             .catch(this.handleError)
    //             .subscribe((data: any) => {
    //                 callback({
    //                     data: data.content,
    //                     draw: data.pageable.draw,
    //                     recordsTotal: data.totalElements,
    //                     recordsFiltered: data.totalElements
    //                 })
    //             })
    //     },
    //     columns: [
    //         { width: '3%', className: 'text-center', render: function (data, display, row) { return '<input type="checkbox" value="' + row.playlistSeq + '">';}},
    //         {data: 'playlistOrd', className: 'text-center playlistOrd', defaultContent: ""},
    //         {data: 'titl', className: 'text-center', defaultContent: ""},
    //         {data: 'xpln', className: 'text-center', defaultContent: ""},
    //         {data: 'regDt', className: 'text-center', defaultContent: "", render: this.dateConvert},
    //         {data: 'update', className: 'text-center', defaultContent: "", render: (data, display, row)=>{return '<button type="button" class="btn btn-default btn-icon" action="Update"><i class="icon-append fa fa-gear" action="Update"></i></button>';}},
    //         {data: 'musicUpdate', className: 'text-center', defaultContent: "", render: ()=>{return '<button type="button" class="btn btn-default btn-icon" action="musicUpdate"><i class="icon-append fa fa-gears" action="musicUpdate"></i></button>';}}
    //     ],
    //     rowCallback: {}
    // };
}
