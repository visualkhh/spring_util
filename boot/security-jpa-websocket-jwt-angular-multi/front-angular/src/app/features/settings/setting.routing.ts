import {RouterModule, Routes} from '@angular/router';
import {SettingComponent} from './setting.component';
import {ModuleWithProviders} from '@angular/core';
export const homeRoutes: Routes = [
    {
        path: '',
        component: SettingComponent,
        data: {
            pageTitle: 'settings'
        },
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(homeRoutes);

