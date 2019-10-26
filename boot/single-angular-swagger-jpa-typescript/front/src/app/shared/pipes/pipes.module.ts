import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FieldFilterPipe } from './field-filter.pipe';
import { MomentPipe } from './moment.pipe';
import { SplitPipe } from './split.pipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [FieldFilterPipe, MomentPipe, SplitPipe],
  exports: [FieldFilterPipe, MomentPipe, SplitPipe]
})
export class PipesModule { }
