import {RouterModule, Routes} from '@angular/router';
import {WebCoreHomeComponent} from "./web-core-home.component";
import {ModuleWithProviders} from "@angular/core";

export const homeRoutes: Routes = [
    {
        path: '',
        component: WebCoreHomeComponent,
        data: {
            pageTitle: 'Home'
        }
    }
];

export const webCoreHomeRouting: ModuleWithProviders = RouterModule.forChild(homeRoutes);

