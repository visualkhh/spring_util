import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {SharedModule} from '@app/shareds/shared.module';


@NgModule({
    imports: [
        CommonModule,
        SharedModule,

        LoginRoutingModule,
    ],
    declarations: [LoginComponent]
})
export class LoginModule {
}
