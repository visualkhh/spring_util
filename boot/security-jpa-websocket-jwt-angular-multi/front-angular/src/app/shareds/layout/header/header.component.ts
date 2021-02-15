import {Component, OnInit} from '@angular/core';
import {UserService} from '@app/services/user.service';
import {filter} from 'rxjs/operators';
import {UserTokenContain} from '@app/models/UserTokenContain';
@Component({
    selector: 'component-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
    private userToken: UserTokenContain;

  constructor(private userService: UserService) {

  }

  ngOnInit() {
      this.userService.user$.pipe(filter(it => it.authorities && it.authorities.length > 0)).subscribe((userTokenContain: UserTokenContain) => {
        this.userToken = userTokenContain;
      });
  }

}
