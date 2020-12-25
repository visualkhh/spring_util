import {ApplicationRef, Injectable} from '@angular/core';
import {languages} from './languages.model';
import {JsonApiService} from '@app/services/json-api.service';
import {BehaviorSubject} from 'rxjs';
import {CoreCode} from '@generate/models';

declare let $: any;
declare let moment: any;

@Injectable()
export class I18nService {

    constructor(private api: JsonApiService, private ref: ApplicationRef) {
        this.initLanguage(this.getLastLocale());
        const data = this.currentLanguage.defaultData;
        for (const k of Object.getOwnPropertyNames(data)){
            this.data[k] = data[k];
        }
        this.fetch(this.currentLanguage.param);
        // if (!I18nService.instance) {
        //     this.initLanguage(this.selectedLocale);
        //     this.fetch(this.currentLanguage.param);
        //     I18nService.instance = this;
        // }
    }

    data: {[key: string]: string} = {};
    public currentLanguage: {
        key: string
        param: string
        alt: string
        title: string,
        defaultData: {[key: string]: string}
    };
    // public state = new BehaviorSubject(this.selectedLocale);
    public i18n$ = new BehaviorSubject({});
    // private static instance: I18nService;
    private id = Date.now();

    private findLanguageByKey(localeKey: string) {
        const language = languages.find((it) => {
            return it.key === localeKey;
        });
        return language;
    }
    private findLanguageByParam(param: string) {
        const language = languages.find((it) => {
            return it.param === param;
        });
        return language;
    }


    private getLastLocale() {
        return window.localStorage.getItem('lastLocale') || 'kr';
    }

    public getTranslation(phrase: string, agument?: any[]): string {
        let rtn = this.data && this.data[phrase] ? this.data[phrase] : phrase;
        const max: number = agument ? agument.length : 0;
        for (let i = 0; i < max; i++) {
            const r = agument[i] || '';
            rtn = rtn.replace(new RegExp('\\{' + i + '\\}', 'gi'), r);
        }
        return rtn;
    }

    // public static getInstance(jsonApiService?: JsonApiService, ref?: ApplicationRef) {
    //     if (!I18nService.instance) {
    //         I18nService.instance = new I18nService(jsonApiService, ref);
    //     }
    //     return I18nService.instance;
    // }

    private fetch(locale: any) {
        this.api.get<any>(`/anons/langs?lang=${locale}`)
            .subscribe((data: any) => {
                this.setData(data);
            }, (e) => {
                this.api.errorHandler(e);
                this.setData(this.findLanguageByParam(locale)?.defaultData);
            });
    }
    private setData(data: {[key: string]: string}) {
        for (const k of Object.getOwnPropertyNames(data)){
            this.data[k] = data[k];
        }
        this.i18n$.next(data);
        try {
            this.ref.tick();
        } catch (e) {
        }
    }

    private initLanguage(locale: string) {
        const language = this.findLanguageByKey(locale);
        if (language) {
            this.currentLanguage = language;
        } else {
            throw new Error(`Incorrect locale used for I18nService: ${locale}`);

        }
    }

    setLanguage(language) {
        this.currentLanguage = language;
        window.localStorage.setItem('lastLocale', language.key);
        this.fetch(language.param);
    }


    subscribe(sub: any, err?: any) {
        return this.i18n$.subscribe(sub, err);
    }

    // public getCodes(code: string, onlyChild = true) {
    //     const data = new Array<Code>();
    //     if (this.data) {
    //         Object.keys(this.data).filter(it => it.startsWith(code) && (onlyChild ? !it.endsWith('000') : true)).forEach(it => {
    //             const codeAt = {
    //                 cd: it,
    //                 cdNm: this.data[it]
    //             } as Code;
    //             data.push(codeAt);
    //         });
    //     }
    //     return data;
    // }

    // public dateFormat(date: string | Date, format = "YYYY-MM-DD HH:mm:ss"): string {
    //     // console.log('--this.currentLanguage'+JSON.stringify(this.currentLanguage))
    //     return moment(date).tz(this.currentLanguage.key == 'us' ? 'America/Los_Angeles' : 'Asia/Seoul').format(format);
    // }

}
