import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {routing} from './brd.routing';
import {SharedModule} from '@app/shareds/shared.module';

@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,

    ],
    providers: [],
    declarations: []
})
export class BrdModule {
}
