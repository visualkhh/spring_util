import {RouterModule, Routes} from '@angular/router';
import {RoomComponent} from './room.component';
import {ModuleWithProviders} from '@angular/core';
export const routes: Routes = [
    {
        path: '',
        component: RoomComponent,
        data: {
            pageTitle: 'rooms'
        },
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);

