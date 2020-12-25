import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@app/shareds/shared.module';
import {AdmComponent} from '@app/features/setups/adms/adm.component';
import {AdmRoutingModule} from '@app/features/setups/adms/adm.routing.module';
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        AdmRoutingModule,
    ],
    providers: [],
    declarations: [AdmComponent]
})
export class AdmModule {
}
