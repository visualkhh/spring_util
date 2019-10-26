import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ParticipantDetailsRouting} from "./participant-details-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ParticipantDetailsComponent} from "./participant-details.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModule} from "@app/features/clinical/participant/participant.module";
import {ParticipantConformityModule} from "@app/features/clinical/participant/details/conformity/participant-conformity.module";
import {ParticipantModalModule} from "@app/features/clinical/participant/modal/participant-modal.module";
import {ParticipantHistoryModule} from "@app/features/clinical/participant/details/history/participant-history.module";
import {ParticipantConcentrationModule} from "@app/features/clinical/participant/details/concentration/participant-concentration.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ParticipantDetailsRouting,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ParticipantModule,
        ParticipantConformityModule,
        ParticipantModalModule,
        ParticipantHistoryModule,
        ParticipantConcentrationModule
    ],
    declarations: [ParticipantDetailsComponent]
})
export class ParticipantDetailsModule {
}
