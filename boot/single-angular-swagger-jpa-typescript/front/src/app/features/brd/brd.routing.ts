import {Routes, RouterModule} from "@angular/router";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'notice',
    pathMatch: 'full'
  },
  {
      path: 'notice',
      loadChildren: './board/board.module#BoardModule',
      data: { cateCd: "BCC001" }
  },
  {
      path: 'help',
      loadChildren: './board/board.module#BoardModule',
      data: { cateCd: "BCC002" }
  }
];

export const routing = RouterModule.forChild(routes);




