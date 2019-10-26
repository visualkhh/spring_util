import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParticipantConcentrationComponent } from "./participant-concentration.component";

const routes: Routes = [{
  path: '',
  component: ParticipantConcentrationComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ParticipantConcentrationRouting { }
