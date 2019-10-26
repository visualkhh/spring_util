import {Directive, ElementRef, OnInit, Input, ApplicationRef, Output, EventEmitter} from '@angular/core';
import {NgModel} from "@angular/forms";


@Directive({
  selector: '[saUiDatepicker]',
  providers: [NgModel]
})
export class UiDatepickerDirective implements OnInit {

  @Input() saUiDatepicker:any;
  @Output() dateChange = new EventEmitter();
  private datepicker: any;
  private element: any;

  constructor(private el:ElementRef, private ngModel: NgModel) {
  // constructor(private el:ElementRef, private ref: ApplicationRef, private ngModel?: NgModel) {
  }

  ngOnInit() {
    // console.log('UiDatepickerDirective ngOnInit --> ', this.ngModel, this.dateChange);
    let onSelectCallbacks = [];
    let saUiDatepicker = this.saUiDatepicker || {};
    this.element = $(this.el.nativeElement);
    // this.element.change = () => {
    //   console.log('aaaaaa')
    // }
    // this.element.keydown =(function(){
    //   alert("The text has been changed.");
    // });
    if (saUiDatepicker.minRestrict) {
      onSelectCallbacks.push((selectedDate)=> {
        $(saUiDatepicker.minRestrict).datepicker('option', 'minDate', selectedDate);
      });
    }
    if (saUiDatepicker.maxRestrict) {
      onSelectCallbacks.push((selectedDate)=> {
        $(saUiDatepicker.maxRestrict).datepicker('option', 'maxDate', selectedDate);
      });
    }

    //Let others know about changes to the data field
    onSelectCallbacks.push((selectedDate) => {
      this.element.triggerHandler("change");

      let form = this.element.closest('form');

      if (typeof form.bootstrapValidator == 'function') {
        try {
          form.bootstrapValidator('revalidateField', this.element);
        } catch (e) {
          console.log(e.message)
        }
      }
    });

    let options = $.extend(saUiDatepicker, {
      prevText: '<i class="fa fa-chevron-left"></i>',
      nextText: '<i class="fa fa-chevron-right"></i>',
      onSelect: (selectedDate, inst) =>{
        onSelectCallbacks.forEach((callback) =>{
          var date = this.element.datepicker("getDate");
          // console.log(date);
          // this.ngModel.update.emit(this.element.datepicker("getDate"));
          this.ngModel.update.emit(selectedDate);
          this.dateChange.emit(date);
          callback.call(callback, selectedDate);
        })
      }
    });



    this.datepicker = this.element.datepicker(options);
    if (options.defaultDate) {
      this.element.datepicker("setDate", options.defaultDate);
    }
    // this.element.datepicker("option", "defaultDate", +7);


  }


}
