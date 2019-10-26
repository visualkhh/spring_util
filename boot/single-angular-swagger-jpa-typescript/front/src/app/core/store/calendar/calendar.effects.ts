import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import {
  CalendarActions,
  CalendarActionTypes,
  LoadEvents,
  LoadEventsSuccess
} from "./calendar.actions";
import { Store } from "@ngrx/store";
import { switchMap, map } from "rxjs/operators";
import { events, samples } from "@app/core/store/calendar/event.mocks";
import { of } from "rxjs";
import { AuthActionTypes } from "@app/core/store/auth";

@Injectable()
export class CalendarEffects {
  @Effect()
  effect$ = this.actions$.pipe(
    ofType(AuthActionTypes.AuthInit),
    switchMap(_ =>
      of({
        events,
        samples
      })
    ),
    map(_ => new LoadEventsSuccess(_))
  );

  constructor(private actions$: Actions, private store: Store<any>) {
  }
}
