import {ApplicationRef, Component, Input, OnInit} from '@angular/core';
import {languages} from '@web-core-app/services/i18n/languages.model';
import {I18nService} from '@web-core-app/services/i18n/i18n.service';
import {JsonApiService} from '@web-core-app/services/json-api.service';

@Component({
  selector: 'web-core-language-selector',
  templateUrl: './language-selector.component.html',
})
export class LanguageSelectorComponent implements OnInit {

  public languages: Array<any>;
  public currentLanguage: any;
  @Input() public class = '';
  // private i18n: I18nService;

  constructor(private i18n: I18nService, private jsonApiService: JsonApiService, private ref: ApplicationRef) {
    // this.i18n.subscribe(it => {
    //   // this.currentLanguage = it;
    //   this.currentLanguage = this.i18n.currentLanguage;
    // });
    // this.i18n = I18nService.getInstance(jsonApiService, ref);
    this.i18n.subscribe(it => {
      this.currentLanguage = this.i18n.currentLanguage;
    });
  }

  ngOnInit() {
    this.languages = languages;
  }

  setLanguage(language){
    this.currentLanguage = language;
    this.i18n.setLanguage(language);
  }

}
