import {RouterModule, Routes} from '@angular/router';
import {BbsComponent} from "./bbs.component";
import {ModuleWithProviders} from "@angular/core";

export const homeRoutes: Routes = [
    {
        path: '',
        component: BbsComponent,
        data: {
            pageTitle: 'Home'
        }
    }
];

export const bbsRouting: ModuleWithProviders = RouterModule.forChild(homeRoutes);

