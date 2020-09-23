import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {SharedModule} from '@web-core-app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {DatatableComponent} from '@web-core-app/features/datatable/datatable.component';
import {QuillModule} from 'ngx-quill';

@NgModule({
    imports: [
        CommonModule,
        LoginRoutingModule,
        SharedModule,
        ProjectSharedModule,
    ],
    declarations: [LoginComponent]
})
export class LoginModule {
}
