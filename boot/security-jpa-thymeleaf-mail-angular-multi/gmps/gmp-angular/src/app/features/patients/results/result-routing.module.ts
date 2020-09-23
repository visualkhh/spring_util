import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PatientComponent} from '@app/features/patients/patient.component';
import {PatientDetailComponent} from '@app/features/patients/details/patient-detail.component';
import {ResultDetailComponent} from '@app/features/patients/results/details/result-detail.component';
import {ResultComponent} from '@app/features/patients/results/result.component';

const routes: Routes = [
    {
        path: '',
        component: ResultComponent,

    },
    {
        path: ':evidenceId',
        // loadChildren: () => import('./details/result-detail.module').then(m => m.ResultDetailModule),
        component: ResultDetailComponent,
        // data: {pageTitle: "Info"}
    }
    ];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ResultRoutingModule {
}
