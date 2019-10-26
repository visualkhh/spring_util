import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ParticipantHistoryRouting} from "./participant-history-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ParticipantHistoryComponent} from "./participant-history.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModule} from "@app/features/clinical/participant/participant.module";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";
import {ConformityDetailsModalModule} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ParticipantHistoryRouting,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ParticipantModule,
        SmartadminInputModule,
        ConformityDetailsModalModule
    ],
    exports: [
        ParticipantHistoryComponent
    ],
    declarations: [ParticipantHistoryComponent]
})
export class ParticipantHistoryModule {
}
