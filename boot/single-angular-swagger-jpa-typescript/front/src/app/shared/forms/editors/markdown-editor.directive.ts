import { Directive, ElementRef, OnInit, Input } from "@angular/core";

import "script-loader!to-markdown/dist/to-markdown.js";
import "script-loader!markdown/lib/markdown.js";
import "script-loader!he/he.js";
import "script-loader!bootstrap-markdown/js/bootstrap-markdown.js";

@Directive({
  selector: "[markdownEditor]"
})
export class MarkdownEditorDirective implements OnInit {
  @Input() markdownEditor: any;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    $(this.el.nativeElement).markdown(this.markdownEditor || {});
  }
}
