import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import { EmptyLayoutComponent } from '@app/shareds/layout/empty/layout/empty-layout.component';
import { MainLayoutComponent } from './app-layouts/main-layout.component';
import {HeaderModule} from './header/header.module';
import {NavigationModule} from './navigation/navigation.module';
import {AuthModule} from '@app/features/auths/auth.module';
import {LoginModule} from '@app/features/auths/login/login.module';
import {PageNotFoundComponent} from '@app/features/errors/page-not-found/page-not-found.component';
import {FooterModule} from '@app/shareds/layout/footer/footer.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    AuthModule,
    LoginModule,
    HeaderModule,
    NavigationModule,
    FooterModule,
  ],
  declarations: [
    MainLayoutComponent,
    EmptyLayoutComponent,
    PageNotFoundComponent,
  ],
  exports: [
    MainLayoutComponent,
    EmptyLayoutComponent,
  ]
})
export class LayoutModule{
}
