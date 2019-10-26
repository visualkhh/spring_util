import { Action } from '@ngrx/store';
import { CalendarActions, CalendarActionTypes } from './calendar.actions';

export interface CalendarState {
  events: Array<any>,
  samples: Array<any>,
  removeAfterDrop: boolean
}

export const initialState: CalendarState = {
  events: [],
  samples: [],
  removeAfterDrop: false,

};

export function calendarReducer(state = initialState, action: any): CalendarState {
  switch (action.type) {

    case CalendarActionTypes.LoadEventsSuccess:
      return {
        ...state,
        events: action.payload.events,
        samples: action.payload.samples
      };

    case CalendarActionTypes.AddSample:
      return {
        ...state,
        samples: [...state.samples].concat(action.payload)
      }

    case CalendarActionTypes.AddEvent:
      return {
        ...state,
        events: [...state.events].concat({...action.payload.event}),
        samples: state.removeAfterDrop ? state.samples.filter(_ => _.id !== action.payload.sampleId) : [...state.samples]
      }


    case CalendarActionTypes.ChangeSampleDrop:
      return {
        ...state,
        removeAfterDrop: action.payload
      }

    default:
      return state;
  }
}
