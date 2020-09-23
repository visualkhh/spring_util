import {RouterModule, Routes} from '@angular/router';
import {BrdComponent} from './brd.component';
import {ModuleWithProviders} from '@angular/core';

export const homeRoutes: Routes = [
    {
        path: '',
        redirectTo: 'notices',
        pathMatch: 'full'
    },
    {
        path: 'notices',
        loadChildren: () => import('./notice/notice.module').then(m => m.NoticeModule)
        // loadChildren: './notice/notice.module#NoticeModule'
        // data: { pageTitle: 'menu.Notice' }
    },
    {
        path: 'qas',
        loadChildren: () => import('./qa/qa.module').then(m => m.QaModule)
        // data: { pageTitle: 'menu.Notice' }
    },
];

export const routing: ModuleWithProviders = RouterModule.forChild(homeRoutes);

