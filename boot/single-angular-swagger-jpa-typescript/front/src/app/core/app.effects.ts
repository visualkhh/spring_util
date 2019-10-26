import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { AppState } from "./store";
import { Store } from "@ngrx/store";
import { AuthActionTypes } from "./store/auth";
import { tap, filter } from "rxjs/operators";
import { environment } from "../../environments/environment";


@Injectable()
export class AppEffects {
  constructor(private actions$: Actions, private store: Store<AppState>) {

    this.store.dispatch({
      type: AuthActionTypes.AppInit
    });

    this.store.select(state => state)
      .pipe(filter(_ => environment.debug))
      .subscribe(_ => {
      window['SMARTADMIN_APP_STATE'] = _
    })
  }


}

