import { Action } from "@ngrx/store";

export enum CalendarActionTypes {
  LoadEvents = "[Calendar] Load Events",
  LoadEventsSuccess = "[Calendar] Load Events Success",
  AddEvent = "[Calendar] Add Event",
  AddSample = "[Calendar] Add Sample",
  ChangeSampleDrop = "[Calendar] Change Sample Drop"
}

export class LoadEvents implements Action {
  readonly type = CalendarActionTypes.LoadEvents;
}

export class LoadEventsSuccess implements Action {
  readonly type = CalendarActionTypes.LoadEventsSuccess;
  constructor(readonly payload: any) {}
}

export class AddEvent implements Action {
  readonly type = CalendarActionTypes.AddEvent;
  constructor(public payload: any) {
    this.payload = {
      sampleId: payload.id,
      event: { ...payload, id : generateEventId() }
    }
  }
}

export class AddSample implements Action {
  readonly type = CalendarActionTypes.AddSample;
  constructor(public payload: any) {
    this.payload.id = generateEventId()
  }
}

export class ChangeSampleDrop implements Action {
  readonly type = CalendarActionTypes.ChangeSampleDrop;
  constructor(readonly payload: any) {}
}

export type CalendarActions =
  | LoadEvents
  | LoadEventsSuccess
  | AddEvent
  | AddSample
  | ChangeSampleDrop;



  export function generateEventId() {
    return Math.random().toString(36).slice(2)
  }
