import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdmComponent} from '@app/features/setups/adms/adm.component';

const routes: Routes = [{
    path: '',
    component: AdmComponent,
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: []
})
export class AdmRoutingModule {
}
