import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { map, tap } from "rxjs/operators";
import { LayoutService } from "@app/core/services/layout.service";
import { LayoutActionTypes } from "./layout.actions";

@Injectable()
export class LayoutEffects {
  @Effect({ dispatch: false })
  onSmartSkin$ = this.actions$.pipe(
    ofType(LayoutActionTypes.SmartSkin),
    tap((action: any) => this.layoutService.onSmartSkin(action.payload))
  );

  @Effect({ dispatch: false })
  onFixedHeader$ = this.actions$.pipe(
    ofType(LayoutActionTypes.FixedHeader),
    tap(action => this.layoutService.onFixedHeader)
  );
  @Effect({ dispatch: false })
  onFixedNavigation$ = this.actions$.pipe(
    ofType(LayoutActionTypes.FixedNavigation),
    tap(action => this.layoutService.onFixedNavigation)
  );
  @Effect({ dispatch: false })
  onFixedRibbon$ = this.actions$.pipe(
    ofType(LayoutActionTypes.FixedRibbon),
    tap(action => this.layoutService.onFixedRibbon)
  );
  @Effect({ dispatch: false })
  onFixedPageFooter$ = this.actions$.pipe(
    ofType(LayoutActionTypes.FixedPageFooter),
    tap(action => this.layoutService.onFixedPageFooter)
  );
  @Effect({ dispatch: false })
  onInsideContainer$ = this.actions$.pipe(
    ofType(LayoutActionTypes.InsideContainer),
    tap(action => this.layoutService.onInsideContainer)
  );
  @Effect({ dispatch: false })
  onRtl$ = this.actions$.pipe(
    ofType(LayoutActionTypes.Rtl),
    tap(action => this.layoutService.onRtl)
  );
  @Effect({ dispatch: false })
  onMenuOnTop$ = this.actions$.pipe(
    ofType(LayoutActionTypes.MenuOnTop),
    tap(action => this.layoutService.onMenuOnTop)
  );
  @Effect({ dispatch: false })
  onColorblindFriendly$ = this.actions$.pipe(
    ofType(LayoutActionTypes.ColorblindFriendly),
    tap(action => this.layoutService.onColorblindFriendly)
  );
  @Effect({ dispatch: false })
  onCollapseMenu$ = this.actions$.pipe(
    ofType(LayoutActionTypes.CollapseMenu),
    tap(action => this.layoutService.onCollapseMenu)
  );
  @Effect({ dispatch: false })
  onMinifyMenu$ = this.actions$.pipe(
    ofType(LayoutActionTypes.MinifyMenu),
    tap(action => this.layoutService.onMinifyMenu)
  );
  @Effect({ dispatch: false })
  onShortcutToggle$ = this.actions$.pipe(
    ofType(LayoutActionTypes.ShortcutToggle),
    tap(action => this.layoutService.onShortcutToggle)
  );

  constructor(
    private actions$: Actions,
    private layoutService: LayoutService
  ) {}
}
