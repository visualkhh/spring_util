import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {homeRouting} from './home.routing';
import {HomeComponent} from "./home.component";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';


@NgModule({
    imports: [
        CommonModule,
        homeRouting,
        SharedModule,
        SmartadminDatatableModule
    ],
    declarations: [HomeComponent]
})
export class HomeModule {
}
