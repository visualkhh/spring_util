import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HeaderComponent} from './header.component';
import {RouterModule} from '@angular/router';
import {SharedModule} from '@app/shareds/shared.module';


@NgModule({
    imports: [
        CommonModule,
        SharedModule,

        RouterModule,
    ],
    declarations: [HeaderComponent],
    exports: [HeaderComponent]
})
export class HeaderModule {
}

