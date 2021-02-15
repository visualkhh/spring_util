import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {routing} from './room.routing';
import {RoomComponent} from './room.component';
import {HomeListComponent} from '@app/features/rooms/modal/home-list.component';
import {SharedModule} from '@app/shareds/shared.module';

@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,
    ],
    declarations: [RoomComponent, HomeListComponent]
})
export class RoomModule {
}
