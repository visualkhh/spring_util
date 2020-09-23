import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";

import { EmptyLayoutComponent } from '@web-core-app/layouts/empty/layout/empty-layout.component';
import { MainLayoutComponent } from './app-layouts/main-layout.component';
import {HeaderModule} from "./header/header.module";
import {FooterComponent} from "./footer/footer.component";
import {NavigationModule} from "./navigation/navigation.module";
import {GraphModule} from "@app/features/graph/graph.module";
import {PageNotFoundComponent} from "@app/features/page-not-found/page-not-found.component";
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
    GraphModule,
    NavigationModule,
  ],
  declarations: [
    MainLayoutComponent,
    EmptyLayoutComponent,
    FooterComponent,
    PageNotFoundComponent,
  ],
  exports:[
    MainLayoutComponent,
    EmptyLayoutComponent,
    FooterComponent
  ]
})
export class LayoutModule{

}
