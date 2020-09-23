import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {PatientDatatableComponent} from '@web-core/app/features/patients/patient.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    PatientDatatableComponent
  ],
  declarations: [PatientDatatableComponent]
})
export class AdmDatatableModule { }
