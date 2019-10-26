import { Action } from '@ngrx/store';
import { LayoutActions, LayoutActionTypes } from './layout.actions';

export interface LayoutState {

}

export const initialLayoutState: LayoutState = {

};

export function layoutReducer(state = initialLayoutState, action: LayoutActions): LayoutState {
  switch (action.type) {

    default:
      return state;
  }
}
