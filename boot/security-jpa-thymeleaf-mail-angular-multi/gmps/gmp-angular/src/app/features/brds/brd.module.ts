import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {routing} from './brd.routing';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,
        ProjectSharedModule
    ],
    providers: [],
    declarations: []
})
export class BrdModule {
}
