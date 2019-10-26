import {Component, OnInit} from '@angular/core';
import {LoginInfoComponent} from "../../user/login-info/login-info.component";
import {UserService} from "@app/core/services";
// import {Observable} from 'rxjs/Observable';
import {Observable} from "rxjs/Rx";
import {UserDetailAuth, UserDetails} from "@app/model/commomModels";
import {it} from "@angular/core/testing/src/testing_internal";
@Component({

  selector: 'sa-navigation',
  templateUrl: './navigation.component.html'
})
export class NavigationComponent implements OnInit {
  constructor(public userService: UserService) {
  // constructor() {
  }

  ngOnInit() {
  }

}
