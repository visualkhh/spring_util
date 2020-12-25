import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BrdDatatableComponent} from '@app/features/brds/datatable/brd.datatable.component';
import {SharedModule} from '@app/shareds/shared.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    BrdDatatableComponent
  ],
  declarations: [BrdDatatableComponent]
})
export class BrdDatatableModule { }
