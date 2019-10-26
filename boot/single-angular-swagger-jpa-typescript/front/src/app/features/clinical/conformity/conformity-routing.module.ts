import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConformityComponent } from "./conformity.component";

const routes: Routes = [{
  path: '',
  component: ConformityComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ConformityRoutingModule { }
