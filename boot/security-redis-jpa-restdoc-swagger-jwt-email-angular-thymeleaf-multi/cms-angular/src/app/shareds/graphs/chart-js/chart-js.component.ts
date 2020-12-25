import {Component, AfterContentInit, ElementRef, Input} from '@angular/core';

import {presets} from './chart-js.presets';

declare var Chart: any;

@Component({

  selector: 'web-core-chart-js',
  template: `
  <div>
  <canvas></canvas>
  </div>
  `,
  styles: []
})
export class ChartJsComponent implements AfterContentInit {

  @Input() public data: any;
  @Input() public type: string;
  @Input() width = '100%';



  constructor(private el: ElementRef) {
  }

  ngAfterContentInit() {
    import('chart.js').then((chartJs: any) => {
      this.render();
    });
  }

  render(){
    const ctx = this.getCtx();
    const data = this.data;

    if (data.datasets && data.datasets.length && presets[this.type] && presets[this.type].dataset){
      data.datasets =  data.datasets.map((it) => {
        return Object.assign({}, presets[this.type].dataset, it);
      });
    }

    const chart = new Chart(ctx, {
      type: this.type,
      data,
      options: presets[this.type] ? presets[this.type].options : {}
    });
    chart.update();

  }

  private getCtx() {
    return this.el.nativeElement.querySelector('canvas').getContext('2d');
  }

  randomScalingFactor() {
    return Math.round(Math.random() * 100);
  }
  randomColorFactor() {
    return Math.round(Math.random() * 255);
  }
  randomColor(opacity) {
    return 'rgba(' + this.randomColorFactor() + ',' + this.randomColorFactor() + ',' + this.randomColorFactor() + ',' + (opacity || '.3') + ')';
  }

}
