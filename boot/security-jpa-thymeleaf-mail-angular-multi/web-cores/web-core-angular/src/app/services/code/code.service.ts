import {Injectable, ApplicationRef} from '@angular/core';
import {JsonApiService} from '@web-core-app/services/json-api.service';
import {BehaviorSubject, Subject} from 'rxjs';
import {Code, UserDetails} from '@web-core-generate/models';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';


declare let moment: any;

@Injectable()
export class CodeService {

    private data: Code[] = [];
    public codes$ = new BehaviorSubject<Code[]>([]);

    constructor(private api: JsonApiService, private ref: ApplicationRef, private i18n: I18nService) {
        this.fetch();
    }

    private fetch() {
        this.api.get<Code[]>(`/anons-web-core/codes`)
            .subscribe((data: Code[]) => {
                this.data = data;
                this.codes$.next(data);
                this.ref.tick();
            }, this.api.errorHandler.bind(this.api));
    }


    public getCode(cd: string): Code {
        return this.codes.filter(it => cd === it.cd)[0];
    }
    public getChildCodes(cd: string): Code[] {
        return this.codes.filter(it => cd === it.prntCd);
    }

    get codes(): Code[] {
        return this.codes$.getValue();
    }


}
