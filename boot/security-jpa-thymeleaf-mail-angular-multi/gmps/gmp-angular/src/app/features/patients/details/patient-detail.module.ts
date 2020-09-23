import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {PatientDetailComponent} from '@app/features/patients/details/patient-detail.component';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        ProjectSharedModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [PatientDetailComponent],
    exports: [PatientDetailComponent]
})
export class PatientDetailModule {
}
