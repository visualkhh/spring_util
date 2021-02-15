import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {routing} from './setting.routing';
import {SettingComponent} from './setting.component';
import {HomeListComponent} from '@app/features/settings/modal/home-list.component';
import {SharedModule} from '@app/shareds/shared.module';

@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,
    ],
    declarations: [SettingComponent, HomeListComponent]
})
export class SettingModule {
}
