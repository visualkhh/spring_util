import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParticipantDetailsComponent } from "./participant-details.component";

const routes: Routes = [{
  path: '',
  component: ParticipantDetailsComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ParticipantDetailsRouting { }
