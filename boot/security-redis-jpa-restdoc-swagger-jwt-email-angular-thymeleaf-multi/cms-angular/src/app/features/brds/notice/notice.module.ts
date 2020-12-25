import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NoticeRoutingModule} from './notice.routing.module';
import {SharedModule} from '@app/shareds/shared.module';

import {NoticeComponent} from './notice.component';

@NgModule({
    imports: [
        CommonModule,
        NoticeRoutingModule,
        SharedModule,

    ],
    declarations: [NoticeComponent]
})
export class NoticeModule {
}
