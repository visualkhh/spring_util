
import {ModuleWithProviders} from "@angular/core"
import {Routes, RouterModule} from "@angular/router";


export const routes:Routes = [
  {
      path: '', redirectTo: 'login/', pathMatch: 'full',
  },
  {
      path: 'login', redirectTo: 'login/', pathMatch: 'full',
  },
  {
    path: 'login/:type',
    loadChildren: './login/login.module#LoginModule'
  },
  // {
  //   path: 'register',
  //   loadChildren: './register/register.module#RegisterModule'
  // },
  // {
  //   path: 'forgot-password',
  //   loadChildren: './forgot/forgot.module#ForgotModule'
  // },
  // {
  //   path: 'locked',
  //   loadChildren: './locked/locked.module#LockedModule'
  // }
];

export const routing = RouterModule.forChild(routes);
