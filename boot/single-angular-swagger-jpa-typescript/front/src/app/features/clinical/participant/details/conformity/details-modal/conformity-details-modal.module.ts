import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConformityDetailsModalComponent } from "./conformity-details-modal.component";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminValidationModule } from '@app/shared/forms/validation/smartadmin-validation.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {SmartadminDatatableModule} from "@app/shared/ui/datatable/smartadmin-datatable.module";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    SmartadminValidationModule,
    FormsModule,
    ReactiveFormsModule,
    SmartadminDatatableModule
  ],
  declarations: [ConformityDetailsModalComponent],
  exports: [ConformityDetailsModalComponent]
})
export class ConformityDetailsModalModule { }
