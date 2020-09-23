import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HeaderComponent} from './header.component';
import {RouterModule} from '@angular/router';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {LanguageSelectorComponent} from '@web-core/app/features/i18n/language-selector/language-selector.component';
import {SignOutComponent} from '@web-core/app/features/auths/signs/sign-out/sign-out.component';
import {UserDetailsModule} from '@web-core/app/features/auths/user-details/user-details.module';
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        ProjectSharedModule,
        RouterModule,
    ],
    declarations: [HeaderComponent, LanguageSelectorComponent, SignOutComponent],
    exports: [
        HeaderComponent
    ]
})
export class HeaderModule {
}

