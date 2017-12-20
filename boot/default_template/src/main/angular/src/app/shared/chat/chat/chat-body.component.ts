import {Component, OnInit, Input, ElementRef} from '@angular/core';
import {ChatService} from "../chat.service";

declare var $: any;

@Component({
  selector: 'chat-body',
  templateUrl: './chat-body.component.html',
})
export class ChatBodyComponent implements OnInit {

  @Input() messages: Array<any>;

  constructor(private chatService: ChatService, private el: ElementRef) { }

  ngOnInit() {
    this.chatService.newMessage.subscribe((message)=>{
      this.messages.push(message);
      this.scrollDown()
    })
  }

  messageTo(user){
    this.chatService.messageTo(user)
  }

  scrollDown(){
    let $body = $('#chat-body', this.el.nativeElement);
    $body.animate({scrollTop: $body[0].scrollHeight});
  }




}
