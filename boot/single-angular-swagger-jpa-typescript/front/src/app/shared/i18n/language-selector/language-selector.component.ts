import {ApplicationRef, Component, OnInit} from '@angular/core';
import {languages} from '../languages.model'
import {I18nService} from "../i18n.service";
import {it} from "@angular/core/testing/src/testing_internal";
import {JsonApiService} from "@app/core/services";

@Component({
  selector: 'sa-language-selector',
  templateUrl: './language-selector.component.html',
})
export class LanguageSelectorComponent implements OnInit {

  public languages: Array<any>;
  public currentLanguage: any;
  private i18n: I18nService;

  constructor(private jsonApiService: JsonApiService, private ref: ApplicationRef) {
    // this.i18n.subscribe(it => {
    //   // this.currentLanguage = it;
    //   this.currentLanguage = this.i18n.currentLanguage;
    // });
    this.i18n = I18nService.getInstance(jsonApiService, ref);
    this.i18n.subscribe(it => {
      this.currentLanguage = this.i18n.currentLanguage;
    });
  }

  ngOnInit() {
    this.languages = languages;
  }

  setLanguage(language){
    this.currentLanguage = language;
    this.i18n.setLanguage(language)
  }

}
