import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { graphRouting } from './graph.routing';
import {GraphComponent} from "./graph.component";
import {SharedModule} from "@app/shared/shared.module";
import {FlotChartComponent} from "@web-core-app/features/graphs/flot-chart/flot-chart.component";
import {ChartJsComponent} from "@web-core-app/features/graphs/chart-js/chart-js.component";

@NgModule({
  imports: [
    CommonModule,
    graphRouting,
    SharedModule
  ],
  declarations: [GraphComponent, FlotChartComponent, ChartJsComponent]
})
export class GraphModule { }
