import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import { Router } from "@angular/router";
import { NotificationService } from "@app/core/services/notification.service";

import {UserService} from "@app/core/services/user.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: "sa-logout",
  template: `
<div id="logout" (click)="showPopup()" class="btn-header transparent pull-right">
        <span> <a title="Sign Out"><i class="fa fa-sign-out"></i></a> </span>
  <form #from action="/security/sign-out" method="post" hidden>
    <input type="text" name="_csrf" value="{{cookieService.get('XSRF-TOKEN')}}">
  </form>
    </div>
  `,
  styles: []
})
export class LogoutComponent implements OnInit {

  public user;
  @ViewChild('from') form: ElementRef;

  constructor(
    private userService: UserService,
    private router: Router,
    private notificationService: NotificationService,
    private cookieService: CookieService
  ) {
  }

  showPopup() {
    this.notificationService.smartMessageBox(
      {
        title:
          // "<i class='fa fa-sign-out txt-color-orangeDark'></i> Logout <span class='txt-color-orangeDark'><strong>" + this.userService.user$.value.username+"</strong></span> ?",
          "<i class='fa fa-sign-out txt-color-orangeDark'></i> Logout <span class='txt-color-orangeDark'></span> ?",
        // content:"열려 있는 이 브라우저를 닫으면 로그아웃 후 보안을 더욱 향상시킬 수 있습니다.",
        buttons: "[No][Yes]"
      },
      ButtonPressed => {
        if (ButtonPressed == "Yes") {
          //this.logout();
          const from: HTMLFormElement = this.form.nativeElement;
          from.submit();
        }
      }
    );
  }

  // logout() {
  //   // this.userService.logout().subscribe((it)=>this.router.navigate(["/"]));
  //   // this.userService.logout().subscribe((it)=>location.href='/');
  //   this.userService.logoutAndForWard();
  // }

  ngOnInit() {}
}
