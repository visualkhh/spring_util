import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRoute } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

import * as fromAuth from '../store/auth';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private store: Store<fromAuth.AuthState>) {
    }

  canActivate(): Observable<boolean> {
    
    return this.store.pipe(
      select(fromAuth.getLoggedIn),
      map(authed => {
        if (!authed) {
          this.store.dispatch(new fromAuth.LoginRedirect('/dashboard'));
          return false;
        }

        return true;
      }),
      take(1)
    );
  }
}
