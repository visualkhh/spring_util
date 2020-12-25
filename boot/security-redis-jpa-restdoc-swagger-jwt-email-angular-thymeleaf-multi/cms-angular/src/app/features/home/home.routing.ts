import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home.component';
import {ModuleWithProviders} from '@angular/core';
import {UserService} from '@app/services/user.service';
export const homeRoutes: Routes = [
    {
        path: '',
        component: HomeComponent,
        data: {
            pageTitle: 'Home'
        },
        canActivate: [UserService]
    }
];

export const homeRouting: ModuleWithProviders = RouterModule.forChild(homeRoutes);

