import { Action } from "@ngrx/store";

export enum LayoutActionTypes {
  SmartSkin = "[Layout]   SmartSkin",
  FixedHeader = "[Layout] FixedHeader",
  FixedNavigation = "[Layout] FixedNavigatio",
  FixedRibbon = "[Layout] FixedRibbon",
  FixedPageFooter = "[Layout] FixedPageFoote",
  InsideContainer = "[Layout] InsideContaine",
  Rtl = "[Layout] Rtl",
  MenuOnTop = "[Layout] MenuOnTop",
  ColorblindFriendly = "[Layout] ColorblindFrie",
  CollapseMenu = "[Layout] CollapseMenu",
  MinifyMenu = "[Layout] MinifyMenu",
  ShortcutToggle = "[Layout] ShortcutToggle"
}

export class SmartSkin implements Action {
  readonly type = LayoutActionTypes.SmartSkin;
  constructor (readonly payload: any){}
}
export class FixedHeader implements Action {
  readonly type = LayoutActionTypes.FixedHeader;
}
export class FixedNavigation implements Action {
  readonly type = LayoutActionTypes.FixedNavigation;
}
export class FixedRibbon implements Action {
  readonly type = LayoutActionTypes.FixedRibbon;
}
export class FixedPageFooter implements Action {
  readonly type = LayoutActionTypes.FixedPageFooter;
}
export class InsideContainer implements Action {
  readonly type = LayoutActionTypes.InsideContainer;
}
export class Rtl implements Action {
  readonly type = LayoutActionTypes.Rtl;
}
export class MenuOnTop implements Action {
  readonly type = LayoutActionTypes.MenuOnTop;
}
export class ColorblindFriendly implements Action {
  readonly type = LayoutActionTypes.ColorblindFriendly;
}
export class CollapseMenu implements Action {
  readonly type = LayoutActionTypes.CollapseMenu;
}
export class MinifyMenu implements Action {
  readonly type = LayoutActionTypes.MinifyMenu;
}
export class ShortcutToggle implements Action {
  readonly type = LayoutActionTypes.ShortcutToggle;
}
export type LayoutActions =
  | SmartSkin
  | FixedHeader
  | FixedNavigation
  | FixedRibbon
  | FixedPageFooter
  | InsideContainer
  | Rtl
  | MenuOnTop
  | ColorblindFriendly
  | CollapseMenu
  | MinifyMenu
  | ShortcutToggle;
