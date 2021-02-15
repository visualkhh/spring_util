import {AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {AlertService} from '@app/services/ui/alert.service';
import {JsonApiService} from '@app/services/json-api.service';

import {Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {UserTokenContain} from '@app/models/UserTokenContain';
import {UserService, WsService} from '@app/services';
import {com} from '@generate/models';
import {BasicModalComponent, ButtonsClickType} from '@app/shareds/modals/basic-modal/basic-modal.component';
import {v4 as uuidv4} from 'uuid';
import {Subscription} from 'rxjs/internal/Subscription';
import Room = com.clone.chat.domain.Room;
import User = com.clone.chat.domain.User;
import RequestCreateRoom = com.clone.chat.controller.ws.rooms.model.RequestCreateRoom;
import Message = com.clone.chat.domain.Message;
import RequestSendRoomMessage = com.clone.chat.controller.ws.rooms.model.RequestSendRoomMessage;

class UserCheck extends User {
    value: boolean;
}

@Component({
    selector: 'app-room',
    templateUrl: './room.component.html',
    styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit, AfterViewInit {

    @ViewChild('modal') modal: BasicModalComponent;
    @ViewChild('roomModal') roomModal: BasicModalComponent;
    @ViewChild('contents') private contents: ElementRef;
    @ViewChildren('contentsItems') contentsItems: QueryList<any>;
    private rooms: Room[];
    private friends: UserCheck[];
    private newRoomName = uuidv4();
    private choiceRoom: Room;
    private roomMessagesSubscription: Subscription;
    private roomMessageSubscription: Subscription;
    private choiceRoomMessages: Message[];
    private userTokenContain: UserTokenContain;

    constructor(private userService: UserService, private wsService: WsService, private alertService: AlertService, public router: Router, private api: JsonApiService) {
        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe((userTokenContain: UserTokenContain) => {
            this.userTokenContain = userTokenContain;
            this.wsService.watch<Room[]>('/user/queue/rooms').subscribe((it) => {
                this.rooms = it;
            });
            // this.wsService.watch<string>(`/topic/rooms/${userTokenContain.authorizationHeaders}`).subscribe((it) => {
            //     console.log(it);
            // });
            this.send();
        });
    }
    ngAfterViewInit() {
        this.scrollToBottom();
        this.contentsItems.changes.subscribe(this.scrollToBottom);
    }
    ngOnInit() {
    }

    send() {
        this.wsService.publish('/app/rooms');
    }

    public getUser() {
        this.wsService.publish('/app/friends');
    }

    createRoom() {
        this.newRoomName = '';
        this.api.get<User[]>('/apis/friends').subscribe(it => {
            this.friends = it.map(sit => Object.assign(new UserCheck(), sit));
        });
    }

    createRoomCheck($event: ButtonsClickType) {
        if ($event.name === 'ok') {
            if(this.newRoomName === '') {
                this.alertService.dangerAlertHttpErrorResponse('error', '방 이름을 입력해주세요');
                return;
            }
            console.log(this.friends);
            const data = new RequestCreateRoom();
            data.name = this.newRoomName;
            data.users = this.friends.filter(it => it.value).map(it => it.id);
            this.wsService.publish('/app/rooms/create-room', data);
            this.modal.close();
        }
    }

    create() {
        this.wsService.publish('/app/rooms/create-room');
    }

    roomCompleted() {

    }

    showRoom(room: Room) {
        this.choiceRoom = room;

        this.roomMessagesSubscription?.unsubscribe();
        this.roomMessageSubscription?.unsubscribe();
        this.roomMessagesSubscription = this.wsService.watch<Message[]>(`/user/queue/rooms/${this.choiceRoom.id}/messages`).subscribe((it) => {
                this.choiceRoomMessages = it;
        });
        this.roomMessageSubscription = this.wsService.watch<Message>(`/user/queue/rooms/${this.choiceRoom.id}/message`).subscribe((it) => {
                this.choiceRoomMessages.push(it);
        });
        this.wsService.publish(`/app/rooms/${this.choiceRoom.id}/messages`);
        this.roomModal.show();
    }

    sendMessage(room: Room, value: string) {
        const requestSendRoomMessage = new RequestSendRoomMessage();
        requestSendRoomMessage.roomId = room.id;
        requestSendRoomMessage.contents = value;
        requestSendRoomMessage.sendDt = new Date();
        this.wsService.publish('/app/rooms/send-messages', requestSendRoomMessage);

    }


    scrollToBottom = () => {
        try {
            this.contents.nativeElement.scrollTop = this.contents.nativeElement.scrollHeight;
        } catch (err) {}
    }
}
