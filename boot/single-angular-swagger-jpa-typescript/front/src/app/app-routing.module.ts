import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";

import {MainLayoutComponent} from "./shared/layout/app-layouts/main-layout.component";
import {AuthLayoutComponent} from "./shared/layout/app-layouts/auth-layout.component";
import {EmptyLayoutComponent} from "@app/shared/layout/app-layouts/empty-layout.component";

const routes: Routes = [
    {
        path: "",
        component: AuthLayoutComponent,
        loadChildren: "./features/auth/auth.module#AuthModule"
    },

    {
        path: "",
        component: MainLayoutComponent,
        data: {pageTitle: "Home"},
        children: [
            {
                path: 'home',
                loadChildren: './features/home/home.module#HomeModule'
            },
            // 게시판 관리
            {
                path: 'brd',
                loadChildren: './features/brd/brd.module#BrdModule',
                data: {pageTitle: "menu.brd"}
            },
            // 임상 관리
            {
                path: 'clinical',
                loadChildren: './features/clinical/clinical.module#ClinicalModule',
                data: {pageTitle: "menu.clinical"}
            },


        ]
    },

    
    // {
    //     path: "**",
    //     // component: AuthLayoutComponent,
    //     // component: EmptyLayoutComponent,
    //     redirectTo: "miscellaneous/error404"
    // }


];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
