// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
// import {Router} from "@angular/router";
// import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '../../../../../../../thesis/thesis-angular/node_modules/@angular/core';
import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@target_angular_project/@angular/core';
// import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
// import {BsModalRef, BsModalService} from "@target/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "../../../../../../../thesis/thesis-angular/node_modules/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "@tar /ngx-bootstrap/modal";

@Component({
  selector: 'basic-modal',
  templateUrl: './basic-modal.component.html',
  styleUrls: ['./basic-modal.component.css']
})
export class BasicModalComponent implements OnInit{
  @ViewChild('modalTemplate') modalTemplate;
  bsModalRef: BsModalRef;

  @Input()
  titleIcon: string;
  @Input()
  public config = {
    // class: 'modal-lg',
    // ignoreBackdropClick: true
  };
  @Input()
  public title;

  @Output() complete = new EventEmitter();

  constructor(private modalService: BsModalService) {
  }

  ngOnInit(): void {
  }

  public show() {
    this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
  }
  // public show<T>(detail: T) {
  //   this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
  // }

  private closeAndComplet() {
    this.complete.emit();
    this.bsModalRef.hide();
  }
}
