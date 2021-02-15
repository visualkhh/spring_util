import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertService} from '@app/services/ui/alert.service';
import {JsonApiService} from '@app/services/json-api.service';

import {Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {HttpClient, HttpParams} from '@angular/common/http';
import {UserService, WsService} from '@app/services';
import {UserTokenContain} from '@app/models/UserTokenContain';
import {com} from '@generate/models';
import {BasicModalComponent, ButtonsClickType} from '@app/shareds/modals/basic-modal/basic-modal.component';
import User = com.clone.chat.domain.User;
import Message = com.clone.chat.domain.Message;
import RequestMessage = com.clone.chat.controller.ws.messages.model.RequestMessage;
import RequestAddFriend = com.clone.chat.controller.api.friends.model.RequestAddFriend;
import File = com.clone.chat.domain.File;

@Component({
    selector: 'app-home',
    templateUrl: './friend.component.html',
    styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

    @ViewChild('userModal') userModal: BasicModalComponent;
    @ViewChild('addFriendModal') addFriendModal: BasicModalComponent;

    private choiceUser: User;
    private findUser: User;
    private friends: User[];
    private userMessage: Message[] = [];
    private userTokenContain: UserTokenContain;

    constructor(private wsService: WsService, private userService: UserService, private http: HttpClient, private alertService: AlertService, public router: Router, private api: JsonApiService) {
    }

    ngOnInit() {
        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe((userTokenContain: UserTokenContain) => {
            this.userTokenContain = userTokenContain;
            this.wsService.watch<User[]>('/user/queue/friends').subscribe((wit) => {
                this.friends = wit;
                this.friends.forEach(a => this.mergeFilePath(a));
                console.log(this.friends)
            });

            this.wsService.watch<Message[]>('/user/queue/messages').subscribe((it) => {
                it.forEach(m => this.userMessage.push(m));
            });

            this.send();
        });
    }

    public send() {
        this.wsService.publish('/app/friends');
    }

    showUser(it: User) {
        this.userMessage = [];
        this.choiceUser = it;
        this.wsService.publish(`/app/messages/${this.choiceUser.id}`);
        this.userModal.show();
    }

    sendMessage(choiceUser: User, value: string) {
        const requestMsg = new RequestMessage();
        requestMsg.contents = value;
        this.wsService.publish(`/app/messages/${this.choiceUser.id}/send`, requestMsg);
    }

    public openAddFriendModal() {
        this.findUser = undefined;
        this.addFriendModal.show();
        this.addFriendModal.resetForm();
    }

    searchFriend(name: string) {
        this.findUser = undefined;
        this.api.get<User>(`/apis/friends/search?userId=${name}`).subscribe(it => {
            this.findUser = it;
            this.mergeFilePath(this.findUser);
        }, this.api.errorHandler.bind(this.api));
    }

    private mergeFilePath(user: User) {
        if (user.file === undefined) {
            user.file = new File();
            user.file.filePath = 'https://dt8upu5cibhp1.cloudfront.net/5db96de3e32fa818dc0d0aecfe82869f';
        }
    }

    addFriend($event: ButtonsClickType) {
        if (this.findUser?.id && 'ok' === $event?.name) {
            const addFriend = new RequestAddFriend();
            addFriend.id = this.findUser.id;
            this.api.post<void>('/apis/friends/add', {params: addFriend}).subscribe(_ => this.addFriendModal.close()
                , (err) => {
                this.alertService.dangerAlertHttpErrorResponse('error', '잘못된 친구추가입니다.');
            });
        }
    }
}
