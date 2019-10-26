import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConformityGiftComponent } from "./conformity-gift.component";

const routes: Routes = [{
  path: '',
  component: ConformityGiftComponent,
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ConformityGiftRouting { }
