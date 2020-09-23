import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {bbsRouting} from './bbs.routing';
import {UserIconComponent} from "@web-core-app/features/userIcon/userIcon.component";
import {BbsComponent} from "./bbs.component";
import {SharedModule} from "@app/shared/shared.module";
import {WowDetailComponent} from "@web-core-app/features/modals/wowDetail/wow-detail.component"
@NgModule({
    imports: [
        CommonModule,
        bbsRouting,
        SharedModule
    ],
    declarations: [BbsComponent, UserIconComponent, WowDetailComponent] // WowDetailComponent
})
export class BbsModule {
}
