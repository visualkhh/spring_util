import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {routing} from './auth.routing';
import {AuthComponent} from './auth.component';
import {SharedModule} from '@app/shareds/shared.module';

import {I18nPipe} from '@app/shareds/pipes/i18n/i18n.pipe';

@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,

    ],
    declarations: [AuthComponent]
})
export class AuthModule {
}
