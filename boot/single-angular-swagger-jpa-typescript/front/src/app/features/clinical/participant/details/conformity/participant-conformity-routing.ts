import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParticipantConformityComponent } from "./participant-conformity.component";

const routes: Routes = [{
  path: '',
  component: ParticipantConformityComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ParticipantConformityRouting { }
