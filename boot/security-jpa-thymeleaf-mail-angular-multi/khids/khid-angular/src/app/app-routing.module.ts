import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import {CrisisListComponent} from "@app/features/crisis-list/crisis-list.component";
// import { MainLayoutComponent } from "@web-core-app/shared/layout/app-layouts/main-layout.component";
// import {EmptyLayoutComponent} from "@web-core-app/shared/layout/app-layouts/empty-layout.component";
import {WebCoreListComponent} from "@web-core-app/features/web-core-list/web-core-list.component";
import { MainLayoutComponent } from "@app/shared/layout/app-layouts/main-layout.component";
import {EmptyLayoutComponent} from "@web-core-app/layouts/empty/layout/empty-layout.component";

const routes: Routes = [
  {
    path: "",
    component: MainLayoutComponent,
    // component: EmptyLayoutComponent,
    data: { pageTitle: "Home" },
    children: [
      {
        path: "",
        redirectTo: "home",
        pathMatch: "full"
      },
      {
        path: "home",
        loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
        // loadChildren: () => import('@web-core-app/features/web-core-home/web-core-home.module').then(m => m.WebCoreHomeModule),
        // loadChildren: () => import('./features/crisis-list/crisis-list.component').then(m => m.HomeListComponent),
        // component: HomeListComponent

        // data: { pageTitle: "Home" }
      },
      {
        path: "bbs",
        loadChildren: () => import('./features/bbs/bbs.module').then(m => m.BbsModule),
      },
      {
        path: "graph",
        loadChildren: () => import('./features/graph/graph.module').then(m => m.GraphModule),
      }
    ]
  },
  {
    path: "web",
    component: WebCoreListComponent,
  },
  {
    path: "m",
    component: MainLayoutComponent,
  },
  {
    path: "c",
    component: CrisisListComponent,
  }

  // {
  //   path: "auth",
  //   component: AuthLayoutComponent,
  //   loadChildren: "./features/auth/auth.module#AuthModule"
  // },
  // { path: "**", redirectTo: "miscellaneous/error404" }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
