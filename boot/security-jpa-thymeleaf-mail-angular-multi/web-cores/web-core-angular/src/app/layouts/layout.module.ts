import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";

import { MainLayoutComponent } from './main/layout/main-layout.component';
import { EmptyLayoutComponent } from './empty/layout/empty-layout.component';
import {HeaderModule} from "./main/header/header.module";
import {FooterComponent} from "./main/footer/footer.component";
import {NavigationModule} from "./main/navigation/navigation.module";
// import {RibbonComponent} from "./ribbon/ribbon.component";
// import {ShortcutComponent} from "./shortcut/shortcut.component";
// import {ToggleActiveDirective} from "../utils/toggle-active.directive";
// import {LayoutSwitcherComponent} from "./layout-switcher.component";
// import { AuthLayoutComponent } from './app-layouts/auth-layout.component';
// import {TooltipModule, BsDropdownModule} from "ngx-bootstrap";
// import { RouteBreadcrumbsComponent } from './ribbon/route-breadcrumbs.component';
// import {UtilsModule} from "../utils/utils.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    HeaderModule,
    NavigationModule,
  ],
  declarations: [
    MainLayoutComponent,
    EmptyLayoutComponent,
    FooterComponent,
  ],
  exports:[
    MainLayoutComponent,
    EmptyLayoutComponent,
    FooterComponent
  ]
})
export class LayoutModule{

}
