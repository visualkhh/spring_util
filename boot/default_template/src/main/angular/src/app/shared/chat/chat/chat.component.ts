import { Component, OnInit } from '@angular/core';
import {ChatService} from "../chat.service";

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styles: []
})
export class ChatComponent implements OnInit {

  constructor(private chatService: ChatService) { }

  public users = [];
  public messages = [];

  ngOnInit() {
    this.chatService.getChatState().subscribe((state)=>{
      this.users = state.users;
      this.messages = state.messages;
    })
  }

}
