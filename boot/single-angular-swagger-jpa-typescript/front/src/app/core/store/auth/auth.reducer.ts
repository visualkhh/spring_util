import { Action } from "@ngrx/store";
import { AuthActions, AuthActionTypes } from "./auth.actions";

export interface AuthState {
  loading: boolean;
  error: any;
  user: any;
  logged: boolean;
  loggedOnce: boolean;
}

export const authInitialState: AuthState = {
  loading: false,
  error: null,
  user: null,
  logged: false,
  loggedOnce: false
};

export function authReducer(
  state = authInitialState,
  action: AuthActions
): AuthState {
  switch (action.type) {
    case AuthActionTypes.LoginAction:
    case AuthActionTypes.SignupAction:
      return {
        ...state,
        loading: true
      };

    case AuthActionTypes.LogoutAction:
    case AuthActionTypes.NullToken:
      return {
        ...state,
        loading: false,
        error: null,
        user: null,
        logged: false
      };

    case AuthActionTypes.LoggedOnce:
      return {
        ...state,
        loggedOnce: action.payload
      };

    case AuthActionTypes.AuthTokenPayload:
    case AuthActionTypes.TokenRestore:
      return {
        ...state,
        error: null,
        loading: false,
        user: action.payload,
        logged: true
      };

    case AuthActionTypes.AuthFailure:
      return {
        ...state,
        error: action.payload,
        loading: false,
        user: null,
        logged: false
      };

    default:
      return state;
  }
}
