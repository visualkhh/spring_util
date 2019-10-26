import {Directive, OnInit, ElementRef, Input} from '@angular/core';

@Directive({
  selector: '[saJquiSpinner]'
})
export class JquiSpinner implements OnInit {

  @Input() saJquiSpinner: any;

  constructor(private el: ElementRef) {
  }

  ngOnInit() {
    $(this.el.nativeElement).spinner(this.saJquiSpinner || {})

  }

}
