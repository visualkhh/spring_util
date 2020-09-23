import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {PatientComponent} from '@app/features/patients/patient.component';
import {PatientRoutingModule} from '@app/features/patients/patient-routing.module';
import {PatientDetailModule} from '@app/features/patients/details/patient-detail.module';

@NgModule({
    imports: [
        CommonModule,
        PatientRoutingModule,
        ProjectSharedModule,
        SharedModule,
        PatientDetailModule,
    ],
    declarations: [PatientComponent]
})
export class PatientModule {
}
