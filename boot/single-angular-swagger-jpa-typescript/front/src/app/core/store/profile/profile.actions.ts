import { Action } from "@ngrx/store";

export enum ProfileActionTypes {
  ProfileUpdate = "[Profile] Update Action",
  ProfileUpdateSuccess = "[Profile API] Update Action Success",
  ProfileUpdateFailure = "[Profile API] Update Action Failure",
  ProfileRestore = "[Profile] Restore Action"
}

export class ProfileUpdate implements Action {
  readonly type = ProfileActionTypes.ProfileUpdate;
  constructor(readonly payload) {}
}

export class ProfileUpdateSuccess implements Action {
  readonly type = ProfileActionTypes.ProfileUpdateSuccess;
  constructor(readonly payload) {}
}

export class ProfileUpdateFailure implements Action {
  readonly type = ProfileActionTypes.ProfileUpdateFailure;
  constructor(readonly payload) {}
}

export class ProfileRestore implements Action {
  readonly type = ProfileActionTypes.ProfileRestore;
  constructor(readonly payload) {}
}

export type ProfileActions =
  | ProfileUpdate
  | ProfileRestore
  | ProfileUpdateSuccess
  | ProfileUpdateFailure;
