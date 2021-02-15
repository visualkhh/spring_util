import {RouterModule, Routes} from '@angular/router';
import {FriendComponent} from './friend.component';
import {ModuleWithProviders} from '@angular/core';
export const homeRoutes: Routes = [
    {
        path: '',
        component: FriendComponent,
        data: {
            pageTitle: 'friend'
        },
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(homeRoutes);

