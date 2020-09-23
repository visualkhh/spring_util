import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PolicyComponent} from '@app/features/setups/policies/policy.component';

const routes: Routes = [{
    path: '',
    component: PolicyComponent,
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: []
})
export class PolicyRoutingModule {
}
