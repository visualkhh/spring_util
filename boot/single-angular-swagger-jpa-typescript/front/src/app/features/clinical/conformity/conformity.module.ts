import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ConformityRoutingModule} from "./conformity-routing.module";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ConformityComponent} from "./conformity.component";
import {UserModule} from "@app/shared/user/user.module";
import {ConformityGiftModule} from "@app/features/clinical/conformity/details/gift/conformity-gift.module";
import {ConformityConformModule} from "@app/features/clinical/conformity/details/conform/conformity-conform.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ConformityRoutingModule,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ConformityGiftModule,
        ConformityConformModule
    ],
    declarations: [ConformityComponent]
})
export class ConformityModule {
}
