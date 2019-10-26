import { Directive, ElementRef, OnInit } from "@angular/core";
import { addClassName, removeClassName } from "../../../utils/dom-helpers";
import "select2/dist/js/select2.min.js";

@Directive({
  selector: "[select2]"
})
export class Select2Directive implements OnInit {
  constructor(private el: ElementRef) {
    addClassName(this.el.nativeElement, ["sa-cloak", "sa-hidden"]);
  }

  ngOnInit() {
    $(this.el.nativeElement).select2();
    removeClassName(this.el.nativeElement, ["sa-hidden"]);
  }
}
