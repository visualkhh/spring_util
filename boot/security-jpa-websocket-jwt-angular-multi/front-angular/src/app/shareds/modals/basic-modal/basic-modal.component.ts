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
export type ButtonsInputType =  {
  name: string,
  // style: string,
  class: string,
  // callback: (data?: any) => unknown;
};
export type ButtonsClickType =  {
  name: string,
  data?: any,
  // callback: (data?: any) => unknown;
};
declare let $: any;
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
  public buttons: ButtonsInputType[] = [];

  @Input()
  public config: {
    class?: string
    disableCloseBtn?: boolean,
    closeBtnName?: string,
    header_class?: string[],
    ignoreBackdropClick?: boolean
  } = {
    // class: 'modal-lg',
    // ignoreBackdropClick: true
    // animated: false
    // initialState:  {
    //   list: [
    //     'Open a modal with component',
    //     'Pass your data',
    //     'Do something else',
    //     '...',
    //     'PROFIT!!!'
    //   ],
    //   title: 'Modal with component',
    //   closeBtnName: 'Close'
    // }
  };

  @Input()
  public title;

  @Output() completed = new EventEmitter();
  @Output() buttonClick = new EventEmitter<ButtonsClickType>();

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
    this.completed.emit();
    this.close();
  }
  public close() {
    this.bsModalRef.hide();
  }

  buttonEmitter(it: ButtonsClickType) {
    // this.buttonClick.subscribe(its => {
    //   console.log('emitter subscripbe', its);
    // });
    this.buttonClick.emit(it);
  }

  public resetForm() {
    $('.modal-body form').each(function() {
      this.reset();
    });
  }
}
