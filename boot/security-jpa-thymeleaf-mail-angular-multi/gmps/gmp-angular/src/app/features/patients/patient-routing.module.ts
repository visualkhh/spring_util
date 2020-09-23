import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PatientComponent} from '@app/features/patients/patient.component';
import {PatientDetailComponent} from '@app/features/patients/details/patient-detail.component';

const routes: Routes = [
    {
        path: '',
        component: PatientComponent,

    },
    {
        path: ':ptntSeq',
        component: PatientDetailComponent,
        // data: {pageTitle: "Info"}
    },
    {
        path: ':ptntSeq/results',
        loadChildren: () => import('./results/result.module').then(m => m.ResultModule),
        // data: {pageTitle: "Info"}
    }
    ];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PatientRoutingModule {
}
