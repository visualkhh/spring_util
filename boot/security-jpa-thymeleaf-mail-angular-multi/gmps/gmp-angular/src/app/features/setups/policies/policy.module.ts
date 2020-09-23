import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {PolicyComponent} from '@app/features/setups/policies/policy.component';
import {PolicyRoutingModule} from '@app/features/setups/policies/policy.routing.module';
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        PolicyRoutingModule,
        ProjectSharedModule
    ],
    providers: [],
    declarations: [PolicyComponent]
})
export class PolicyModule {
}
