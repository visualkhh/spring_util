import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ParticipantConformityRouting} from "./participant-conformity-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ParticipantConformityComponent} from "./participant-conformity.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModule} from "@app/features/clinical/participant/participant.module";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";
import {ConformityDetailsModalModule} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ParticipantConformityRouting,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ParticipantModule,
        SmartadminInputModule,
        ConformityDetailsModalModule
    ],
    exports: [
        ParticipantConformityComponent
    ],
    declarations: [ParticipantConformityComponent]
})
export class ParticipantConformityModule {
}
