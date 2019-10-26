import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import {
  ProfileActions,
  ProfileActionTypes,
  ProfileRestore,
  ProfileUpdateSuccess,
  ProfileUpdateFailure
} from "./profile.actions";
import { tap, filter, map, catchError, switchMap } from "rxjs/operators";

import * as fromAuth from "../auth";

// import { fireApp } from "../../firebase";
import { Observable } from "rxjs";
import { Store } from "@ngrx/store";
import { ProfileState } from ".";
import { createProfile } from "./profile.model";
@Injectable()
export class ProfileEffects {


  @Effect()
  profileUpdate$ = this.actions$.pipe(
    ofType(ProfileActionTypes.ProfileUpdate),
    map((_: any) => _.payload),
    // tap(data => this.uid = data.uid),
    // @todo save in databse
    map(data => new ProfileUpdateSuccess(data)),
  );

  @Effect()
  effect$ = this.actions$.pipe(
    ofType(fromAuth.AuthActionTypes.AuthTokenPayload),
    filter(_ => !!_),
    map((_: any) => _.payload),
    // @todo restore profile data
    map(data => new ProfileRestore(data))
  );

  constructor(private actions$: Actions, private store: Store<ProfileState>) {}
}
