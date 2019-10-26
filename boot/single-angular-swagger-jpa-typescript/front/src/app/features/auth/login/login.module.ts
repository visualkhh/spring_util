import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {I18nModule} from "@app/shared/i18n/i18n.module";
@NgModule({
    imports: [
        CommonModule,
        LoginRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        I18nModule
    ],
    declarations: [LoginComponent]
})
export class LoginModule {
}
