import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NoticeRoutingModule} from './notice.routing.module';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {NoticeComponent} from './notice.component';

@NgModule({
    imports: [
        CommonModule,
        NoticeRoutingModule,
        SharedModule,
        ProjectSharedModule,
    ],
    declarations: [NoticeComponent]
})
export class NoticeModule {
}
