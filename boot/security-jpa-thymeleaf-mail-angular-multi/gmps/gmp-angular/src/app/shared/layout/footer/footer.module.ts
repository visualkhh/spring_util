import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {FooterComponent} from './footer.component';
import {RouterModule} from '@angular/router';
import {SharedModule} from '@web-core-app/shareds/shared.module';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RouterModule
        // SharedModule
    ],
    declarations: [FooterComponent],
    exports: [FooterComponent]
})
export class FooterModule {
}
