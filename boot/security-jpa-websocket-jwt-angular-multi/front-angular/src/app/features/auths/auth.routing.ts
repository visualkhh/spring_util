import {RouterModule, Routes} from '@angular/router';


export const routes: Routes = [
    {
        path: '', redirectTo: 'login/', pathMatch: 'full',
    },
    {
        path: 'login', redirectTo: 'login/', pathMatch: 'full',
    },
    {
        path: 'login/:type',
        loadChildren: './login/login.module#LoginModule'
    },
];

export const routing = RouterModule.forChild(routes);
