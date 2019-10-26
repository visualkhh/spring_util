import {Directive, Input, OnInit, HostListener} from '@angular/core';

import 'summernote/dist/summernote.min.js'

@Directive({
  selector: '[summernoteAttach]'
})
export class SummernoteAttachDirective implements OnInit{

  @Input() summernoteAttach: any;
  @HostListener('click') render(){
    $(this.summernoteAttach).summernote({
      focus: true
    })
  }

  constructor() {  }

  ngOnInit(){}
}
