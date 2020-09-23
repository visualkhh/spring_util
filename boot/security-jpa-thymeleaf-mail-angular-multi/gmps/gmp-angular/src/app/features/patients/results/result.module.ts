import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SharedModule} from '@web-core/app/shareds/shared.module';
import {SharedModule as ProjectSharedModule} from '@app/shared/shared.module';
import {ResultComponent} from '@app/features/patients/results/result.component';
import {ResultRoutingModule} from '@app/features/patients/results/result-routing.module';
import {ResultDetailComponent} from '@app/features/patients/results/details/result-detail.component';
@NgModule({
    imports: [
        CommonModule,
        ResultRoutingModule,
        ProjectSharedModule,
        SharedModule,
    ],
    declarations: [ResultComponent, ResultDetailComponent]
})
export class ResultModule {
}
