import { Directive, Input, ElementRef } from "@angular/core";

import * as Dropzone from "dropzone";
Dropzone.autoDiscover = false;
@Directive({
  selector: "[saDropzone]"
})
export class DropzoneDirective {
  @Input() saDropzone: any;

  private dropzone: any;

  constructor(private el: ElementRef) {
    this.initDropzone();
  }

  initDropzone() {
    this.dropzone = new Dropzone(this.el.nativeElement, this.saDropzone || {
      url: 'http://respondto.it/',
    });
  }
}
