

import {NgModule, ModuleWithProviders} from "@angular/core";
import {CommonModule} from "@angular/common";
import {LoginInfoComponent} from "./login-info/login-info.component";
import {LogoutComponent} from "./logout/logout.component";

@NgModule({
  imports: [CommonModule],
  declarations: [LoginInfoComponent, LogoutComponent],
  exports: [LoginInfoComponent, LogoutComponent]
})
export class UserModule{}
