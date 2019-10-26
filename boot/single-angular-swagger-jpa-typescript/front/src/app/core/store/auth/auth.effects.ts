import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import * as actions from './auth.actions';

import { tap, filter, switchMap, map } from 'rxjs/operators';

// import { fireApp, fireAuth } from '../../firebase';
import { AuthState } from './auth.reducer';
import { Store } from '@ngrx/store';
import { AuthTokenService } from '../../services/auth-token.service';
import { Router, ActivatedRoute } from '@angular/router';
import { environment } from '../../../../environments/environment';

// const auth = fireApp.auth();

@Injectable()
export class AuthEffects {
  redirectUrl: string = '/dashboard';
  loginUrl: string = '/login';

  @Effect({ dispatch: false })
  login$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.LoginAction),
    tap((data: any) => {
      // auth
      //   .signInWithEmailAndPassword(
      //     data.payload.username,
      //     data.payload.password
      //   )
      //   .catch(this.dispatchError);
    })
  );

  @Effect({ dispatch: false })
  logout$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.LogoutAction),
    tap((data: any) => {
      this.router.navigate(['']);
      // auth.signOut();
    })
  );

  @Effect({ dispatch: false })
  signup$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.SignupAction),
    tap((data: any) => {
      // auth
      //   .createUserWithEmailAndPassword(
      //     data.payload.username,
      //     data.payload.password
      //   )
      //   .catch(this.dispatchError);
    })
  );

  @Effect({ dispatch: false })
  googleSign$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.GoogleSign),
    tap((data: any) => {
      // auth
      //   .signInWithPopup(new fireAuth.GoogleAuthProvider())
      //   .catch(this.dispatchError);
    })
  );

  @Effect({ dispatch: false })
  loginRedirect$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.LoginRedirect),
    tap((data: any) => {
      this.redirectUrl = data.payload || '';
      this.router.navigate([this.loginUrl]);
    })
  );

  @Effect({ dispatch: false })
  authRedirect$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.AuthTokenPayload),
    filter((_:any) => this.router.url === this.loginUrl),
    tap((data: any) => {
      this.router.navigate([this.redirectUrl]);
    })
  );

  @Effect()
  authUser$ = this.actions$.pipe(
    ofType(actions.AuthActionTypes.AuthUserChange),
    // switchMap((data: any) => data.payload.getIdToken()),
    tap((_: any) => (this.authToken.token = _)),
    map((_: any) => this.authToken.readPayload(_)),

    map((_: any) => new actions.AuthTokenPayload(_))
  );



  dispatchError = (err:any) => {
    this.store.dispatch(
      new actions.AuthFailure({
        code: err.code,
        message: err.message
      })
    );
  };

  constructor(
    private actions$: Actions,
    private store: Store<AuthState>,
    private authToken: AuthTokenService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    // auth.onAuthStateChanged(data => {
    //   // console.log('\n\n onAuthStateChanged', data);
    // });

    // auth.onIdTokenChanged(authUser => {
    //   // console.log('\n\n onIdTokenChanged', data);
    //   if (authUser) {
    //     this.store.dispatch(new actions.AuthUserChange(authUser));
    //   } else {
    //     this.authToken.token = null;
    //     this.store.dispatch(new actions.NullToken());
    //   }
    // });


  }
}
