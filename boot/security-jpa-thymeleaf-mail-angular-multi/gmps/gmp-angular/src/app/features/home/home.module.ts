import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {homeRouting} from './home.routing';
import {HomeComponent} from './home.component';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
@NgModule({
    imports: [
        CommonModule,
        homeRouting,
        SharedModule,
        ProjectSharedModule,
    ],
    declarations: [HomeComponent, HomeListComponent]
})
export class HomeModule {
}
