import { Injectable } from '@angular/core';
import {JsonApiService} from "../../core/services/json-api.service";
import { Subject } from 'rxjs';

@Injectable()
export class ChatService {

  url: string;

  public messageToSubject;
  public newMessage;

  constructor(private jsonApiService: JsonApiService) {
    this.url = '/chat/chat.json';
    this.messageToSubject = new Subject();
    this.newMessage = new Subject();
  }


  getChatState()  {
    return this.jsonApiService.fetch(this.url)

  }

  messageTo(user){
    this.messageToSubject.next(user)
  }

  sendMessage(message){
    this.newMessage.next(message)

  }



}
