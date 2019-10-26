import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParticipantModalComponent } from "./participant-modal.component";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminValidationModule } from '@app/shared/forms/validation/smartadmin-validation.module';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        SmartadminValidationModule,
        FormsModule,
        ReactiveFormsModule,
        SmartadminValidationModule,
        SmartadminInputModule,
        SharedModule,
    ],
  declarations: [ParticipantModalComponent],
  exports: [ParticipantModalComponent]
})
export class ParticipantModalModule { }
