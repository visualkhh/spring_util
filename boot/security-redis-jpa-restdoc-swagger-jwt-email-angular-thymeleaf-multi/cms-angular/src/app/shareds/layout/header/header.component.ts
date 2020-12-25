import {Component, OnInit} from '@angular/core';
import {UserService} from '@app/services/user.service';
@Component({
    selector: 'component-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userService: UserService) {

  }

  ngOnInit() {
  }

}
