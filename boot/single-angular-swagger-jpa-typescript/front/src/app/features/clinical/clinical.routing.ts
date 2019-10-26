import {Routes, RouterModule} from "@angular/router";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'conformity',
    pathMatch: 'full'
  },
  {
      path: 'conformity',
      loadChildren: './conformity/conformity.module#ConformityModule',
      data: {pageTitle: 'menu.clinical.conformity'}
  },
  {
      path: 'participant',
      loadChildren: './participant/participant.module#ParticipantModule',
      data: {pageTitle: 'menu.clinical.participant'}
  }
];

export const routing = RouterModule.forChild(routes);




