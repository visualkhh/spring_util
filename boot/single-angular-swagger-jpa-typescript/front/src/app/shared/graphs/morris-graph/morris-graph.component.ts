import {
  Component,
  OnInit,
  ElementRef,
  AfterContentInit,
  Input
} from "@angular/core";

import "script-loader!raphael";
import "script-loader!morris.js/morris.js";
declare var Morris: any;

@Component({
  selector: "sa-morris-graph",
  template: `
     <div class="chart no-padding" ></div>
  `,
  styles: []
})
export class MorrisGraphComponent implements AfterContentInit {
  @Input() public data: any;
  @Input() public options: any;
  @Input() public type: string;

  constructor(private el: ElementRef) {}

  ngAfterContentInit() {
    let options = this.options || {};

    options.element = this.el.nativeElement.children[0];
    options.data = this.data;

    switch (this.type) {
      case "area":
        Morris.Area(options);
        break;
      case "bar":
        Morris.Bar(options);
        break;
      case "line":
        Morris.Line(options);
        break;
      case "donut":
        Morris.Donut(options);
        break;
    }
  }
}
