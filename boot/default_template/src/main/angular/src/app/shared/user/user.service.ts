import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs/Rx";

import {JsonApiService} from "../../core/api/json-api.service";

@Injectable()
export class UserService {

  public user: Subject<any>;

  public userInfo = {
    username: 'Guest'
  };

  constructor(private jsonApiService:JsonApiService) {
    this.user = new Subject();
  }

  getLoginInfo():Observable<any> {
    return this.jsonApiService.fetch('/user/login-info.json')
      .do((user)=>{
        this.userInfo = user;
      this.user.next(user)
    })
  }

}
