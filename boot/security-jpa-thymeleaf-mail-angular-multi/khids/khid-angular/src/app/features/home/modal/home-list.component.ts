import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-home-list',
  templateUrl: './home-list.component.html',
  styleUrls: ['./home-list.component.css']
})
export class HomeListComponent implements OnInit{
  @ViewChild('modalTemplate') modalTemplate;
  bsModalRef: BsModalRef;
  @Output() complete = new EventEmitter();
  constructor(private router: Router, private modalService: BsModalService) {
  }

  ngOnInit(): void {
  }

  public show(detail: any) {
    this.bsModalRef = this.modalService.show(this.modalTemplate, {ignoreBackdropClick: true});
  }

  private closeAndComplet() {
    this.complete.emit();
    this.bsModalRef.hide();
  }

}
