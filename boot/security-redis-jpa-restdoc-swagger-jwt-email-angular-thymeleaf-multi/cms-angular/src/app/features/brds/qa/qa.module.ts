import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {QaRoutingModule} from './qa.routing.module';
import {SharedModule} from '@app/shareds/shared.module';

import {QaComponent} from './qa.component';
import {InlineNavigationModule} from "@app/shareds/navigation/inline-navigation/inline-navigation.module";

@NgModule({
    imports: [
        CommonModule,
        QaRoutingModule,
        SharedModule,
        InlineNavigationModule,

    ],
    declarations: [QaComponent]
})
export class QaModule {
}
