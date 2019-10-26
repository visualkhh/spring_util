import {Directive, OnInit, ElementRef, Input} from '@angular/core';

@Directive({
  selector: '[saJquiAutocomplete]'
})
export class JquiAutocomplete implements OnInit {

  @Input() saJquiAutocomplete: any;

  constructor(private el: ElementRef) {
  }

  ngOnInit() {
    $(this.el.nativeElement).autocomplete(this.saJquiAutocomplete || {})

  }

}
