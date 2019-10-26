import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GiftModalComponent } from "./gift-modal.component";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminValidationModule } from '@app/shared/forms/validation/smartadmin-validation.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    SmartadminValidationModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [GiftModalComponent],
  exports: [GiftModalComponent]
})
export class GiftModalModule { }
