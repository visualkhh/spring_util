import {RouterModule, Routes} from '@angular/router';
import {BrdComponent} from './brd.component';
import {ModuleWithProviders} from '@angular/core';
import {UserService} from '@app/services/user.service';

export const homeRoutes: Routes = [
    {
        path: '',
        redirectTo: 'notices',
        pathMatch: 'full'
    },
    {
        path: 'notices',
        loadChildren: () => import('./notice/notice.module').then(m => m.NoticeModule),
        canActivate: [UserService]
        // loadChildren: './notice/notice.module#NoticeModule'
        // data: { pageTitle: 'menu.Notice' }
    },
    {
        path: 'qas',
        loadChildren: () => import('./qa/qa.module').then(m => m.QaModule),
        canActivate: [UserService]
        // data: { pageTitle: 'menu.Notice' }
    },
];

export const routing: ModuleWithProviders = RouterModule.forChild(homeRoutes);

