import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ConformityGiftRouting} from "./conformity-gift-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ConformityGiftComponent} from "./conformity-gift.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModule} from "@app/features/clinical/participant/participant.module";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";
import {ConformityDetailsModalModule} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.module";
import {GiftModalModule} from "@app/features/clinical/conformity/details/gift/modal/gift-modal.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ConformityGiftRouting,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ParticipantModule,
        SmartadminInputModule,
        ConformityDetailsModalModule,
        GiftModalModule
    ],
    exports: [
        ConformityGiftComponent
    ],
    declarations: [ConformityGiftComponent]
})
export class ConformityGiftModule {
}
