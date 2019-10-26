import { Action } from "@ngrx/store";
import { NotifyActions, NotifyActionTypes } from "./notify.actions";

export interface NotifyState {
  error: {
    code: string;
    message: string;
  };
}

export const initialNotifyState: NotifyState = {
  error: {
    code: null,
    message: null
  }
};

export function notifyReducer(
  state = initialNotifyState,
  action: NotifyActions
): NotifyState {
  switch (action.type) {
    case NotifyActionTypes.ShowError:
      return {
        ...state,
        error: {
          code: action.payload.code,
          message: action.payload.message
        }
      };

    default:
      return state;
  }
}
