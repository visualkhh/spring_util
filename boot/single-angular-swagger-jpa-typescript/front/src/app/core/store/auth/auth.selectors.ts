import * as fromAuth from './auth.reducer'

import { createSelector, createFeatureSelector } from '@ngrx/store';


export const getAuthState = createFeatureSelector<fromAuth.AuthState>('auth')


export const getAuthLoading = createSelector(getAuthState, (state: fromAuth.AuthState) => state.loading)
export const getAuthError = createSelector(getAuthState, (state: fromAuth.AuthState) => state.error)
export const getUser = createSelector(getAuthState, (state: fromAuth.AuthState) => state.user)

export const getLoggedIn = createSelector(getAuthState, (state: fromAuth.AuthState) => !!state.user)

