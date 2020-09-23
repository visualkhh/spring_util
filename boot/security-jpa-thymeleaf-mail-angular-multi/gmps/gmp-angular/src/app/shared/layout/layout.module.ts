import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

import { EmptyLayoutComponent } from '@web-core-app/layouts/empty/layout/empty-layout.component';
import { MainLayoutComponent } from './app-layouts/main-layout.component';
import {HeaderModule} from './header/header.module';
import {FooterComponent} from './footer/footer.component';
import {NavigationModule} from './navigation/navigation.module';
import {AuthModule} from '@app/features/auths/auth.module';
import {LoginModule} from '@app/features/auths/login/login.module';
import {PageNotFoundComponent} from '@app/features/errors/page-not-found/page-not-found.component';
import {FooterModule} from '@app/shared/layout/footer/footer.module';

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
    // FooterComponent,
    PageNotFoundComponent,
  ],
  exports: [
    MainLayoutComponent,
    EmptyLayoutComponent,
    // FooterComponent,
  ]
})
export class LayoutModule{
}
