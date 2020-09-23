import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {CorpDatatableComponent} from '@web-core/app/features/setups/corps/datatable/corp.datatable.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    CorpDatatableComponent
  ],
  declarations: [CorpDatatableComponent]
})
export class CorpDatatableModule { }
