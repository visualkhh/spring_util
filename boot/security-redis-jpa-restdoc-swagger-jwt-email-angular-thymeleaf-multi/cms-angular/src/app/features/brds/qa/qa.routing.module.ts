import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {QaComponent} from './qa.component';

const routes: Routes = [{
    path: '',
    component: QaComponent,
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: []
})
export class QaRoutingModule {
}
