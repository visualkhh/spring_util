import {Component, OnInit} from '@angular/core';
import {UserService} from '@app/services/user.service';
import {filter} from 'rxjs/operators';
import {com} from "@generate/models";
import User = com.clone.chat.domain.User;
import {Router} from "@angular/router";
import {JsonApiService} from "@app/services";
import File = com.clone.chat.domain.File;
import {UserTokenContain} from "@app/models/UserTokenContain";

declare let $: any;
@Component({
    selector: 'component-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

    private user: User;
    private userTokenContain: UserTokenContain;

    constructor(public userService: UserService, private api: JsonApiService) {
    }

    ngOnInit() {
        this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe((userTokenContain: UserTokenContain) => {
            this.userTokenContain = userTokenContain;
            const url = "/anon-apis/users/" + this.userTokenContain.id;
            this.api.get<User>(url).subscribe(it => {
            this.user = it;

            this.mergeFilePath(this.user);
        }, this.api.errorHandler.bind(this.api));
    })
    }

    private mergeFilePath(user: User) {
        if (user.file === undefined) {
            user.file = new File();
            user.file.filePath = 'https://dt8upu5cibhp1.cloudfront.net/5db96de3e32fa818dc0d0aecfe82869f';
        }
    }

    // https://getbootstrap.com/docs/4.0/components/collapse/
    closeCollapse() {
        $('.collapse').collapse('hide');
    }
}
