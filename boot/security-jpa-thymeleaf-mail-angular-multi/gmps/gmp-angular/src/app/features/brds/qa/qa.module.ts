import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {QaRoutingModule} from './qa.routing.module';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {QaComponent} from './qa.component';

@NgModule({
    imports: [
        CommonModule,
        QaRoutingModule,
        SharedModule,
        ProjectSharedModule,
    ],
    declarations: [QaComponent]
})
export class QaModule {
}
