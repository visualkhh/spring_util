import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {routing} from './friend.routing';
import {FriendComponent} from './friend.component';
import {HomeListComponent} from '@app/features/friends/modal/home-list.component';
import {SharedModule} from '@app/shareds/shared.module';

@NgModule({
    imports: [
        CommonModule,
        routing,
        SharedModule,
    ],
    declarations: [FriendComponent, HomeListComponent]
})
export class FriendModule {
}
