import {Component, OnInit, ElementRef, Input, Directive, HostBinding} from '@angular/core';

@Directive({
  selector: '[saDraggableEvent]',
})
export class DraggableEvent implements OnInit {

  @Input() event: any;

  @HostBinding('class') type: any;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    this.type = this.event.className;

    $(this.el.nativeElement)
      .data('eventObject', this.event)
      .draggable({
        zIndex: 999,
        revert: true, // will cause the event to go back to its
        revertDuration: 0 //  original position after the drag
      });
  }

}
