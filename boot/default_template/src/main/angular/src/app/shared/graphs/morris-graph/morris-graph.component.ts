import {Component, OnInit, ElementRef, AfterContentInit, Input} from '@angular/core';

declare var Morris:any;

@Component({

  selector: 'sa-morris-graph',
  template: `
     <div class="chart no-padding" ></div>
  `,
  styles: []
})
export class MorrisGraphComponent implements AfterContentInit {

  @Input() public data:any;
  @Input() public options:any;
  @Input() public type:string;

  constructor(private el:ElementRef) {
  }

  ngAfterContentInit() {

    System.import('script-loader!raphael').then(()=> {
      return System.import('morris.js/morris.js')
    }).then(()=> {
      options.element = this.el.nativeElement.children[0];
      options.data = this.data;

      switch (this.type) {
        case 'area':
          Morris.Area(options);
          break;
        case 'bar':
          Morris.Bar(options);
          break;
        case 'line':
          Morris.Line(options);
          break;
        case 'donut':
          Morris.Donut(options);
          break;
      }
    });
    let options = this.options || {};


  }

}
