import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {CorpComponent} from '@app/features/setups/corps/corp.component';
import {CorpRoutingModule} from '@app/features/setups/corps/corp.routing.module';
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        CorpRoutingModule,
        ProjectSharedModule
    ],
    providers: [],
    declarations: [CorpComponent]
})
export class CorpModule {
}
