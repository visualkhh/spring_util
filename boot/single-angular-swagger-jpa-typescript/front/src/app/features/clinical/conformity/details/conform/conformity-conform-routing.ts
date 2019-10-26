import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConformityConformComponent } from "./conformity-conform.component";

const routes: Routes = [{
  path: '',
  component: ConformityConformComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ConformityConformRouting { }
