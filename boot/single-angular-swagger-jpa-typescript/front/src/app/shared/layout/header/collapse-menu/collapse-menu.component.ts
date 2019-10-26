import {Component, OnInit} from '@angular/core';
import {LayoutService} from "@app/core/services/layout.service";

@Component({
  selector: 'sa-collapse-menu',
  templateUrl: './collapse-menu.component.html'
})
export class CollapseMenuComponent {

  constructor(
    private layoutService: LayoutService
  ) {

  }

  onToggle() {
    this.layoutService.onCollapseMenu()
  }
}
