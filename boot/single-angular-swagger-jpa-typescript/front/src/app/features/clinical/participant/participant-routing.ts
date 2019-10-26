import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParticipantComponent } from "./participant.component";



export const routes: Routes = [
  {
    path: '',
    component: ParticipantComponent,
  },
  {
    path: ':userSeq',
    loadChildren: './details/participant-details.module#ParticipantDetailsModule',
    data: {pageTitle: 'Detail'}
    // component: ParticipantComponent,
  },
  // {
  //   path: 'participant',
  //   loadChildren: './participant/participant.module#ParticipantModule',
  // }
];

export const routing = RouterModule.forChild(routes);
