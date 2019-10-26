import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { NotifyActions, NotifyActionTypes } from './notify.actions';
import { map } from 'rxjs/operators';
import { NotifyService } from './notify.service';

@Injectable()
export class NotifyEffects {

  @Effect({ dispatch: false })
  showError$ = this.actions$.pipe(
    ofType(NotifyActionTypes.ShowError),
    map((_: any) => ({ code: _.payload.code, message: _.payload.message })),
    map(_ => this.notifyService.showError(_))
  );

  @Effect({ dispatch: false })
  snakNotify$ = this.actions$.pipe(
    ofType(NotifyActionTypes.SnackNotify),
    map((_: any) => _.payload),
    map(_ => this.notifyService.snackNotify(_))
  );

  constructor(private actions$: Actions, private notifyService: NotifyService) {}
  


}
