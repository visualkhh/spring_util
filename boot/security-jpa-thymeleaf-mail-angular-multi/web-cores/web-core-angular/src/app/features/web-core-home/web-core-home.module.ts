import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { webCoreHomeRouting } from './web-core-home.routing';

import {WebCoreHomeComponent} from "./web-core-home.component";

@NgModule({
  imports: [
    CommonModule,
    webCoreHomeRouting,
  ],
  declarations: [WebCoreHomeComponent]
})
export class WebCoreHomeModule { }
