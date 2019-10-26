import { Action } from "@ngrx/store";
import { ProfileActions, ProfileActionTypes } from "./profile.actions";

import * as fromAuth from "../auth";
import { Profile, DefaultProfile, createProfile } from "./profile.model";

export interface ProfileState {
  loading: boolean;
  model: Profile;
  error: any;
}

export const initialProfileState: ProfileState = {
  loading: true,
  model: { ...DefaultProfile },
  error: null
};

export function profileReducer(
  state = initialProfileState,
  action: ProfileActions | fromAuth.AuthActions
): ProfileState {
  switch (action.type) {
    case fromAuth.AuthActionTypes.AuthTokenPayload:
      return {
        ...state,
        model: createProfile(action.payload)
      };

    case ProfileActionTypes.ProfileRestore:
      return {
        ...state,
        loading: false,
        model: { ...action.payload }
      };
      
    case ProfileActionTypes.ProfileUpdate:
      return {
        ...state,
        loading: true,
        model: { ...action.payload }
      };
      
    case ProfileActionTypes.ProfileUpdateSuccess:
      return {
        ...state,
        loading: false,
        error: null
      };

    case ProfileActionTypes.ProfileUpdateFailure:
      return {
        ...state,
        loading: false,
        error: action.payload
      };

    default:
      return state;
  }
}
