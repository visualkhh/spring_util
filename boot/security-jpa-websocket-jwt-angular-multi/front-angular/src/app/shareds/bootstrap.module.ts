import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {ModalModule} from 'ngx-bootstrap/modal';
import {ButtonsModule} from 'ngx-bootstrap/buttons';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ProgressbarModule} from 'ngx-bootstrap/progressbar';
import {AlertModule} from 'ngx-bootstrap/alert';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {CarouselModule} from 'ngx-bootstrap/carousel';
// import {AnimationBuilder} from '@angular/animations';

// import { PopoverModule } from "ngx-popover";

export const imports = [
  CommonModule,
  ModalModule.forRoot(),
  ButtonsModule.forRoot(),
  TooltipModule.forRoot(),
  BsDropdownModule.forRoot(),
  ProgressbarModule.forRoot(),
  AlertModule.forRoot(),
  TabsModule.forRoot(),
  AccordionModule.forRoot(),
  CarouselModule.forRoot()
];
export const exports = [
  ModalModule,
  ButtonsModule,
  TooltipModule,
  BsDropdownModule,
  ProgressbarModule,
  AlertModule,
  TabsModule,
  AccordionModule,
  CarouselModule,
];
export const declarations = [];



@NgModule({
  imports: [
    CommonModule,
    ModalModule.forRoot(),
    ButtonsModule.forRoot(),
    TooltipModule.forRoot(),
    BsDropdownModule.forRoot(),
    ProgressbarModule.forRoot(),
    AlertModule.forRoot(),
    TabsModule.forRoot(),
    AccordionModule.forRoot(),
    CarouselModule.forRoot()
  ],
  exports: [
    ModalModule,
    ButtonsModule,
    TooltipModule,
    BsDropdownModule,
    ProgressbarModule,
    AlertModule,
    TabsModule,
    AccordionModule,
    CarouselModule,
    // PopoverModule
  ],
  declarations: []
})
export class BootstrapModule {}
