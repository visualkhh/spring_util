import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms'
import {ParticipantConcentrationRouting} from "./participant-concentration-routing";
import { SharedModule } from '@app/shared/shared.module';
import { SmartadminDatatableModule } from '@app/shared/ui/datatable/smartadmin-datatable.module';
import {ParticipantConcentrationComponent} from "./participant-concentration.component";
import {UserModule} from "@app/shared/user/user.module";
import {ParticipantModule} from "@app/features/clinical/participant/participant.module";
import {SmartadminInputModule} from "@app/shared/forms/input/smartadmin-input.module";
import {ConformityDetailsModalModule} from "@app/features/clinical/participant/details/conformity/details-modal/conformity-details-modal.module";
import {FlotChartModule} from "@app/shared/graphs/flot-chart/flot-chart.module";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ParticipantConcentrationRouting,
        UserModule,
        SharedModule,
        SmartadminDatatableModule,
        ParticipantModule,
        SmartadminInputModule,
        ConformityDetailsModalModule,
        FlotChartModule
    ],
    exports: [
        ParticipantConcentrationComponent
    ],
    declarations: [ParticipantConcentrationComponent]
})
export class ParticipantConcentrationModule {
}
