import {Directive, ElementRef, OnInit} from '@angular/core';

import 'script-loader!ion-rangeslider/js/ion.rangeSlider.min.js'

@Directive({
  selector: '[ionSlider]'
})
export class IonSliderDirective implements OnInit{

  constructor(private el: ElementRef) { }

  ngOnInit(){
    $(this.el.nativeElement).ionRangeSlider();
  }

}
