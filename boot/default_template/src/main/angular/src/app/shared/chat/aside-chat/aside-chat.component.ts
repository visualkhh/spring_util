import {Component, OnInit, ViewChild} from '@angular/core';
import {ChatService} from "../chat.service";

declare var $: any;

@Component({
  selector: 'aside-chat',
  templateUrl: './aside-chat.component.html',
  styles: []
})
export class AsideChatComponent implements OnInit {

  @ViewChild('chatUsersList') chatUsersList;

  public state:{
    open:boolean,
    filter:string
  }

  constructor(private chatService: ChatService) {
    this.state = {
      open: false,
      filter: ''
    }
  }


  public users: Array<any> = [];

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
      if(!this.state.filter){
        it.visible = true
      } else {
        it.visible = it.username.toLowerCase().indexOf(this.state.filter.toLocaleLowerCase()) > -1;
      }

    })
  }

  openToggle(e) {
    this.state.open = !this.state.open;
    $(this.chatUsersList.nativeElement).slideToggle();
    e.preventDefault()
  }
}
