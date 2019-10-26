import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from '@env/environment';
import { distinctUntilChanged, switchMap } from 'rxjs/operators';
import { StorageService } from './storage.service';
import { Store } from '@ngrx/store';

import { TokenRestore, AuthInit, LoggedOnce } from '../store/auth/auth.actions';
import { AuthState } from '../store/auth/auth.reducer';

const ROLE_ADMIN = 1;

const USER_TOKEN = 'token';
const USER_LOGGED_ONCE = 'logged_once';

@Injectable()
export class AuthTokenService {
  public token$ = new BehaviorSubject(null);

  constructor(
    private storage: StorageService,
    private store: Store<AuthState>
  ) {}

  load(): Promise<any> {
    return new Promise((resolve, reject) => {
      this.store.dispatch(new AuthInit());

      this.storage.get(USER_TOKEN).then(
        token => {
          environment.log.auth &&
            console.log((!!token ? "logged" : "not logged") + " at boot");

          if (!!token) {
            try {
              let payload = this.readPayload(token);
              this.store.dispatch(new TokenRestore(payload));
            } catch (error) {
              token = null;
            }
          }

          this.token = token;

          this.token$
            .pipe(switchMap(this.dumpToken), switchMap(this.updateLoggedOnce))
            .subscribe(() => {});
          resolve(token);
        },
        error => {
          resolve(null);
        }
      );
    });
  }

  dumpToken = (token: any) => {
    environment.log.auth &&
      console.log("\n\n\n================\ndump auth token", token);
    return !!token
      ? this.storage.set(USER_TOKEN, token)
      : this.storage.remove(USER_TOKEN).then(() => null)
  };

  updateLoggedOnce = (token: any) => {
    return this.storage.get(USER_LOGGED_ONCE).then(loggedOnce => {
      if (token || loggedOnce) {
        this.store.dispatch(new LoggedOnce(true));
        return loggedOnce
          ? token
          : this.storage.set(USER_LOGGED_ONCE, Date.now()).then(_ => token);
      } else {
        return Promise.resolve(token);
      }
    });
  };

  set token(value) {
    this.token$.next(value);
  }
  get token() {
    return this.token$.value;
  }

  readPayload(token: any) {
    let payload = this.getTokenPayload(token);
    return payload; // && payload.user ? Object.assign({roles: [], id: null},
    // {id: payload.user.id, roles: JSON.parse(payload.user.roles)}) : null
  }

  getTokenPayload(token: any) {
    return token
      ? JSON.parse(this.b64DecodeUnicode(token.split(".")[1]))
      : null;
  }

  b64DecodeUnicode(str:any) {
    // Going backwards: from bytestream, to percent-encoding, to original string.
    return decodeURIComponent(
      atob(str)
        .split("")
        .map(function(c) {
          return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join("")
    );
  }
}

export function AuthTokenFactory(service: AuthTokenService): Function {
  return () => service.load();
}
