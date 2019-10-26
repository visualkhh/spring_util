import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";

import { CalendarWidgetComponent } from "./calendar-widget/calendar-widget.component";
import { DraggableEvent } from "./draggable-event/draggable-event.directive";
import { EventSamplesComponent } from "./event-samples/event-samples.component";
import { AddSampleEvent } from "./add-sample-event/add-sample-event.component";
import { UtilsModule } from "@app/shared/utils/utils.module";
import { SmartadminWidgetsModule } from "@app/shared/widgets/smartadmin-widgets.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    UtilsModule,
    SmartadminWidgetsModule,
    BsDropdownModule],

  declarations: [
    AddSampleEvent,
    CalendarWidgetComponent,
    DraggableEvent,
    EventSamplesComponent
  ],
  exports: [
    AddSampleEvent,
    CalendarWidgetComponent,
    DraggableEvent,
    EventSamplesComponent
  ],
  providers: []
})
export class CalendarComponentsModule {}
