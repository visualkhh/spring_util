import {RouterModule, Routes} from '@angular/router';
import {GraphComponent} from "./graph.component";
import {ModuleWithProviders} from "@angular/core";

export const homeRoutes: Routes = [
    {
        path: '',
        component: GraphComponent,
        data: {
            pageTitle: 'Home'
        }
    }
];

export const graphRouting: ModuleWithProviders = RouterModule.forChild(homeRoutes);

