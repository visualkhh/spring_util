import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import { ChatWidgetComponent } from './chat-widget/chat-widget.component';
import {SmartadminWidgetsModule} from "@app/shared/widgets/smartadmin-widgets.module";
import { ChatComponent } from './chat/chat.component';
import { ChatUsersComponent } from './chat/chat-users.component';
import { ChatBodyComponent } from './chat/chat-body.component';
import { ChatFormComponent } from './chat/chat-form.component';
import {FormsModule} from "@angular/forms";
import {UtilsModule} from "@app/shared/utils/utils.module";
import {PipesModule} from "@app/shared/pipes/pipes.module";
import {UserModule} from "@app/shared/user/user.module";
import { AsideChatComponent } from './aside-chat/aside-chat.component';
import { AsideChatUserComponent } from './aside-chat-user/aside-chat-user.component';
import {PopoverModule} from "ngx-popover";
import {BsDropdownModule} from "ngx-bootstrap"

@NgModule({
  imports: [
      PopoverModule, BsDropdownModule,
    CommonModule, FormsModule, UtilsModule, PipesModule, UserModule, SmartadminWidgetsModule],
  declarations: [ChatWidgetComponent, ChatComponent, ChatUsersComponent, ChatBodyComponent, ChatFormComponent, AsideChatComponent, AsideChatUserComponent],
  exports: [ChatWidgetComponent, AsideChatComponent, AsideChatUserComponent ],
  providers: []

})
export class ChatModule{}
