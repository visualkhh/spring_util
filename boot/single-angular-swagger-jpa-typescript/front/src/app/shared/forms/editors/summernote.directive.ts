import {
  Directive,
  ElementRef,
  Input,
  OnInit,
  Output,
  EventEmitter
} from "@angular/core";

import "summernote/dist/summernote.min.js";

@Directive({
  selector: "[summernote]"
})
export class SummernoteDirective implements OnInit {
  @Input() summernote = {};
  @Output() change = new EventEmitter();

  constructor(private el: ElementRef) {}

  ngOnInit() {
    $(this.el.nativeElement).summernote(
      Object.assign(this.summernote, {
        tabsize: 2,
        callbacks: {
          onChange: (we, contents, $editable) => {
            this.change.emit(contents);
          }
        }
      })
    );
  }
}
