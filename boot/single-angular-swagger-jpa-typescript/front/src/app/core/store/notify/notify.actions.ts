import { Action } from "@ngrx/store";

export enum NotifyActionTypes {
  SnackNotify = "[Notify] Snack Notify",
  ShowError = "[Error] Show Error"
}

export class SnackNotify implements Action {
  readonly type = NotifyActionTypes.SnackNotify;
  readonly silent = true
  constructor(public payload: any) {}
}

export class ShowError implements Action {
  readonly type = NotifyActionTypes.ShowError;
  constructor(public payload: any) {}
}

export type NotifyActions = SnackNotify | ShowError;
