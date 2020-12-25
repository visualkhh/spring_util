import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {homeRouting} from './home.routing';
import {HomeComponent} from './home.component';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {SharedModule} from '@app/shareds/shared.module';
import {InlineNavigationModule} from "@app/shareds/navigation/inline-navigation/inline-navigation.module";

@NgModule({
    imports: [
        CommonModule,
        homeRouting,
        SharedModule,
        InlineNavigationModule,

    ],
    declarations: [HomeComponent, HomeListComponent]
})
export class HomeModule {
}
