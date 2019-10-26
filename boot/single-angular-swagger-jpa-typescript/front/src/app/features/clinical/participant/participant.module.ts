import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {routing} from "./participant-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ParticipantComponent} from "./participant.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModalModule} from "./modal/participant-modal.module";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        routing,
        UserModule,
        ParticipantModalModule,
        SharedModule,
        SmartadminDatatableModule,
        SmartadminInputModule
    ],
    exports: [
        ParticipantComponent
    ],
    declarations: [ParticipantComponent]
})
export class ParticipantModule {
}
