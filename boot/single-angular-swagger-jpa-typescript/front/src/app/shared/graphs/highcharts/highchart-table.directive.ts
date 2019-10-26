import { Directive, ElementRef, OnInit, Input } from "@angular/core";

import "script-loader!highcharts";
import "script-loader!smartadmin-plugins/bower_components/highchartTable/jquery.highchartTable.js";

@Directive({
  selector: "[saHighchartTable]"
})
export class HighchartTable implements OnInit {
  @Input() saHighchartTable: any;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    $(this.el.nativeElement).highchartTable();
  }
}
