import {Routes, RouterModule} from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'patients',
        pathMatch: 'full'
    },
    {
        path: 'patients',
        loadChildren: () => import('../patients/patient.module').then(m => m.PatientModule),
        // data: { pageTitle: "menu.Patient" }
    }
    ];

export const routing = RouterModule.forChild(routes);




