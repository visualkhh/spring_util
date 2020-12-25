import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CorpComponent} from '@app/features/setups/corps/corp.component';

const routes: Routes = [{
    path: '',
    component: CorpComponent,
}];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    providers: []
})
export class CorpRoutingModule {
}
