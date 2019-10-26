import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParticipantHistoryComponent } from "./participant-history.component";

const routes: Routes = [{
  path: '',
  component: ParticipantHistoryComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ParticipantHistoryRouting { }
