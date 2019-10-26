import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routing } from './clinical.routing';
import { SmartadminValidationModule } from '@app/shared/forms/validation/smartadmin-validation.module';
import { SmartadminInputModule } from '@app/shared/forms/input/smartadmin-input.module';
import { SharedModule } from '@app/shared/shared.module';
@NgModule({
    imports: [
        CommonModule,
        SmartadminValidationModule,
        SmartadminInputModule,
        SharedModule,
        routing
    ],
    declarations: []
})
export class ClinicalModule { }
