import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {BoardRoutingModule} from "./board-routing.module";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {BoardComponent} from "./board.component";
import {UserModule} from "@app/shared/user/user.module";
import {BrdModalModule} from "../brd-modal/brd-modal.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        BoardRoutingModule,
        UserModule,
        BrdModalModule,
        SharedModule,
        SmartadminDatatableModule
    ],
    declarations: [BoardComponent]
})
export class BoardModule {
}
