import { Injectable } from "@angular/core";

@Injectable()
export class NotifyService {
  constructor() {}

  public showError = error => {
      // @todo show error
  };

  public snackNotify = (message, action: string = 'Ok', duration: number = 7000) => {
    // @todo show notify
  };
}
