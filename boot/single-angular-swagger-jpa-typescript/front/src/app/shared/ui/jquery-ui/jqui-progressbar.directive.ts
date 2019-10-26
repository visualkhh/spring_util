import {Directive, OnInit, ElementRef, Input} from '@angular/core';

@Directive({
  selector: '[saJquiProgressbar]',
})
export class JquiProgressbar implements OnInit {

  @Input() saJquiProgressbar: any;

  constructor(private el: ElementRef) {
  }

  ngOnInit() {
    $(this.el.nativeElement).progressbar(this.saJquiProgressbar || {})

  }

}
