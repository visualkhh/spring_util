import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HeaderComponent} from './header.component';
import {RouterModule} from '@angular/router';
import {SharedModule} from '@app/shareds/shared.module';

import {LanguageSelectorComponent} from '@app/shareds/i18n/language-selector/language-selector.component';
import {SignOutComponent} from '@app/features/auths/signs/sign-out/sign-out.component';
import {UserDetailsModule} from '@app/features/auths/user-details/user-details.module';
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

