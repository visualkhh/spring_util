import {Component, OnInit, Input} from '@angular/core';
import {ChatService} from "../chat.service";
import {UserService} from "../../user/user.service";

@Component({
  selector: 'chat-form',
  templateUrl: './chat-form.component.html',
})
export class ChatFormComponent implements OnInit {

  public message: string = '';

  public user: any;

  public enterToSend: boolean = false;

  constructor(private chatService: ChatService, private userService: UserService) { }


  ngOnInit() {
    this.chatService.messageToSubject.subscribe((user)=>{
      this.message += (user.username + ', ');
    });

    this.user = this.userService.userInfo;
    this.userService.user.subscribe((user)=>{
      this.user = user
    })
  }

  sendMessage(){
    if(this.message.trim() == '') return;
    this.chatService.sendMessage({
      body: this.message,
      user: this.user,
      date: new Date()
    });
    this.message = ''

  }

  sendMessageEnter(){
    if(this.enterToSend){
      this.sendMessage()
    }
  }

}
