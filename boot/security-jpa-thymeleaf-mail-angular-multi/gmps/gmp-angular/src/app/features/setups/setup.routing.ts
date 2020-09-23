import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {UserService} from '@web-core-app/services/user.service';

export const homeRoutes: Routes = [
    {
        path: '',
        redirectTo: 'policies',
        pathMatch: 'full'
    },
    {
        path: 'policies',
        loadChildren: () => import('./policies/policy.module').then(m => m.PolicyModule),
        canActivate: [UserService]
        // loadChildren: './notice/notice.module#NoticeModule'
        // data: { pageTitle: 'menu.Notice' }
    },
    {
        path: 'adms',
        loadChildren: () => import('./adms/adm.module').then(m => m.AdmModule),
        canActivate: [UserService]
    },
    {
        path: 'corps',
        loadChildren: () => import('./corps/corp.module').then(m => m.CorpModule),
        canActivate: [UserService]
    },
];

export const routing: ModuleWithProviders = RouterModule.forChild(homeRoutes);

