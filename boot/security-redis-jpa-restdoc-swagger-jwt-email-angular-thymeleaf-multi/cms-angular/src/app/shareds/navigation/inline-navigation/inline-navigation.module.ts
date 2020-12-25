import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@app/shareds/shared.module';
import {InlineNavigationComponent} from '@app/shareds/navigation/inline-navigation/inline-navigation.component';
import {RouterModule} from '@angular/router';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RouterModule
    ],
    exports: [
        InlineNavigationComponent
    ],
    declarations: [InlineNavigationComponent]
})
export class InlineNavigationModule {
}
