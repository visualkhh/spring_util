import { Injectable, Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { AuthTokenService } from './auth-token.service';

import { environment } from '@env/environment';
import { Store } from '@ngrx/store';

import * as fromAuth from '../store/auth';
import { catchError } from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(
    private injector: Injector,
    public authToken: AuthTokenService,
    public store: Store<fromAuth.AuthState>
  ) {}
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (request.url.search('YOUR_API_ENDPOINT') === 0) {
      // attach tcken
      return this.handleApiRequest(request, next);
    } else {
      return next.handle(request);
    }
  }



  handleApiRequest(request, next) {
    request = this.authToken.token
      ? request.clone({
          setHeaders: {
            Authorization: `Bearer ${this.authToken.token}`
          }
        })
      : request;

    const handler = next.handle(request).pipe(
      catchError((error, caught) => {
        if (error.status === 401 || error.status === 403) {

          this.store.dispatch(new fromAuth.LogoutAction());
          return throwError(error);
        }  else {
          return throwError(error);
        }
      })
    );

    return handler;
  }
}
