import {Injectable, ApplicationRef} from '@angular/core';
import {JsonApiService} from '@app/services/json-api.service';
import {BehaviorSubject, Subject} from 'rxjs';
import {CoreCode, UserDetails, UseCd} from '@generate/models';
import {I18nService} from '@app/services/i18n/i18n.service';


declare let moment: any;

@Injectable()
export class CodeService {

    public UseCd = UseCd;
    private data: CoreCode[] = [];
    public codes$ = new BehaviorSubject<CoreCode[]>([]);

    constructor(private api: JsonApiService, private ref: ApplicationRef, private i18n: I18nService) {
        this.fetch();
    }

    private fetch() {
        this.api.get<CoreCode[]>(`/anons/codes`)
            .subscribe((data: CoreCode[]) => {
                this.data = data;
                this.codes$.next(data);
                this.ref.tick();
            }, this.api.errorHandler.bind(this.api));
    }


    public getCode(cd: string): CoreCode {
        return this.codes.filter(it => cd === it.cd)[0];
    }
    public getChildCodes(cd: string): CoreCode[] {
        return this.codes.filter(it => cd === it.prntCd);
    }

    get codes(): CoreCode[] {
        return this.codes$.getValue();
    }


}
