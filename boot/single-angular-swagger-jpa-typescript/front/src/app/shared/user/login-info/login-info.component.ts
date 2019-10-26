import {Component, OnInit} from '@angular/core';
import { UserService } from '@app/core/services/user.service';
import { LayoutService } from '@app/core/services/layout.service';

@Component({

  selector: 'sa-login-info',
  templateUrl: './login-info.component.html',
})
export class LoginInfoComponent implements OnInit {


  constructor(
    public us: UserService,
              private layoutService: LayoutService) {
  }

  ngOnInit() {
  }

  toggleShortcut() {
    this.layoutService.onShortcutToggle()
  }

}
