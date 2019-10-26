import {Component, OnInit, ViewChild, HostBinding} from '@angular/core';
import {ChatService} from "@app/core/services/chat.service";

@Component({
  selector: 'aside-chat,[aside-chat]',
  templateUrl: './aside-chat.component.html',
  styles: [`:host{display:block}`]
})
export class AsideChatComponent implements OnInit {

  @ViewChild('chatUsersList') chatUsersList;
  @HostBinding('class') classes = 'chat-users top-menu-invisible';
  @HostBinding('class.open') open = false;

  public filter:string = ''
  public users: Array<any> = [];

  constructor(private chatService: ChatService) {}

  ngOnInit() {
    this.chatService.getChatState().subscribe((state)=>{
      this.users = state.users.map((it)=>{
        it.visible = true;
        return it
      });
    })
  }

  onFilter(){
    this.users.forEach((it)=>{
      if(!this.filter){
        it.visible = true
      } else {
        it.visible = it.username.toLowerCase().indexOf(this.filter.toLocaleLowerCase()) > -1;
      }

    })
  }

  openToggle(e) {
    this.open = !this.open
    $(this.chatUsersList.nativeElement).slideToggle();
    e.preventDefault()
  }
}
