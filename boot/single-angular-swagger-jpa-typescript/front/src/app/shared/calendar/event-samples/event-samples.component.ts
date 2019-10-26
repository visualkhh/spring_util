import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'sa-event-samples',
  templateUrl: './event-samples.component.html',
})
export class EventSamplesComponent{

  @Input() calendar;
  @Output() changeSampleDrop = new EventEmitter()

  public removeAfterDrop = false;

  toggleRemoveAfterDrop() {
    this.changeSampleDrop.emit(this.removeAfterDrop)
  }

}
