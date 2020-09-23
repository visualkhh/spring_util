import {NgModule} from '@angular/core';
import {Route, RouterModule, Routes, UrlMatchResult, UrlSegment, UrlSegmentGroup} from '@angular/router';
import {MainLayoutComponent} from '@app/shared/layout/app-layouts/main-layout.component';
import {EmptyLayoutComponent} from '@web-core-app/layouts/empty/layout/empty-layout.component';
import {UserService} from '@web-core-app/services/user.service';
import {PageNotFoundComponent} from '@app/features/errors/page-not-found/page-not-found.component';

const routes: Routes = [
    {
        path: '',
        component: EmptyLayoutComponent,
        loadChildren: () => import('./features/auths/auth.module').then(m => m.AuthModule),
    },
    {
        path: '',
        component: MainLayoutComponent,
        // data: {pageTitle: 'Home'},
        children: [
            {
                path: 'home',
                loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
                canActivate: [UserService]
            },
            {
                path: 'brds',
                loadChildren: () => import('./features/brds/brd.module').then(m => m.BrdModule),
                canActivate: [UserService]
            },
            {
                // http://localhost:4200/#/patients/1/evidences/2617
                // http://localhost:4200/#/patients/1/evidences/2617?viewtype=strip
                path: 'patients',
                loadChildren: () => import('./features/patients/patient.module').then(m => m.PatientModule),
                canActivate: [UserService]
            },
            {
                path: 'setups',
                loadChildren: () => import('./features/setups/setup.module').then(m => m.SetupModule),
                canActivate: [UserService]
            },
        ]
    },
    // {
    //     path: 'result',
    //     // matcher: (segments: UrlSegment[], group: UrlSegmentGroup, route: Route): UrlMatchResult => {
    //     //     if (segments.length === 1 && segments[0].path.match(/^@[\w]+$/gm)) {
    //     //         return {
    //     //             consumed: segments,
    //     //             posParams: {
    //     //                 username: new UrlSegment(segments[0].path.substr(1), {})
    //     //             }
    //     //         };
    //     //     }
    //     //     return null;
    //     // },
    //     component: EmptyLayoutComponent,
    //     children: [
    //         {
    //             path: 'patients',
    //             loadChildren: () => import('./features/patients/patient.module').then(m => m.PatientModule),
    //             // canActivate: [UserService]
    //         }
    //     ]
    // },

    // {
    //   path: "auth",
    //   component: AuthLayoutComponent,
    //   loadChildren: "./features/auth/auth.module#AuthModule"
    // },
    {path: '**', component: PageNotFoundComponent}

];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true, enableTracing: false})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
