import {AfterViewInit, Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {JsonApiService} from '@web-core/app/services/json-api.service';
import {Custom, InputInfo, Term} from '@web-core-generate/models';
import {forkJoin, from} from 'rxjs/index';
import {map, mergeMap} from 'rxjs/operators';
import {InputVariantInfoSameCount} from '@web-core-generate/models';

declare let $: any;
declare let moment: any;

export type TermsMap = { [id: number]: Term; };

export interface TermDup extends Term {
    dup?: boolean;
}
export interface CustomSameCount extends Custom {
    sameCount?: number;
}

export interface InputVariantInfoSameCountAndSub extends InputVariantInfoSameCount {
   sub?: {
       disease: string;
       omim: string;
       inheritance: string;
   }[];
}


@Component({
    selector: 'app-patient-regist',
    templateUrl: './result-detail.component.html',
    styleUrls: ['./result-detail.component.css'],
    providers: []
})
export class ResultDetailComponent implements OnInit, OnDestroy, AfterViewInit {

    public evidenceId: number;
    public ptntSeq: number;

    private inputInfo: InputInfo; // 사용자 정보 (입력값)
    private inputInfoTerms: TermDup[]; // 사용자 정보에서 추출된 PhenoType 의 terms 값
    private custom: CustomSameCount; // evidences 정보, 결과 variant 값
    private customTerms: TermDup[]; // evidences 정보에서 추출된 PhenoType 의 terms 값
    private inputVariantInfo: InputVariantInfoSameCountAndSub[]; // 대상 ViriantInfo

    constructor(private route: ActivatedRoute, private api: JsonApiService) {
        // console.log(route.snapshot.paramMap);
        this.evidenceId = Number(route.snapshot.paramMap.get('evidenceId'));
        this.ptntSeq = Number(route.snapshot.paramMap.get('ptntSeq'));
    }


    ngOnInit() {
        const inputInfoObservable = this.api.get<InputInfo>(`/patients/${this.ptntSeq}/results/inputs`);
        const customObservable = this.api.get<CustomSameCount>(`/evidences/${this.evidenceId}`);
        const inputVariantInfoObservable = this.api.get<InputVariantInfoSameCountAndSub[]>(`/patients/${this.ptntSeq}/results/variants/${this.evidenceId}`);
        forkJoin([inputInfoObservable, customObservable, inputVariantInfoObservable])
            .pipe(
                map((it: [InputInfo, CustomSameCount, InputVariantInfoSameCountAndSub[]]) => {
                    this.inputInfo = it[0]; // 사용자 입력
                    this.custom = it[1];  // 결과 evidence
                    this.inputVariantInfo = it[2]; // 대상 variants
                    this.inputVariantInfo.forEach((v) => {
                        v.sub = new Array<any>();
                        const ds = (v.inputVariant?.disease || '//').split('//');
                        const os = (v.inputVariant?.phenotypeMIMnumber || '//').split('//');
                        const is = (v.inputVariant?.inheritance || '//').split('//');
                        for (let i = 0; i < ds.length; i++) {
                            const sub = {} as any;
                            sub.disease = ds[i] || '-';
                            sub.omim = os[i] || '-';
                            sub.inheritance = is[i] || '-';
                            v.sub.push(sub);
                        }
                    });

                    if (this.custom.inheritance) {
                        // NONE (no information), // XRL (X-linked Recessive) , // XDL (X-linked Dominant), // AR (Autosomal Recessive), // AD (Autosomal Dominant), // Y (Y-linked), // M (Mitochondrial)
                        this.custom.inheritance = this.custom.inheritance
                            .replace(/NONE/g, 'no information')
                            .replace(/XDL/g, 'X-linked Recessive')
                            .replace(/XRL/g, 'X-linked Dominant')
                            .replace(/AR/g, 'Autosomal Recessive')
                            .replace(/AD/g, 'Autosomal Dominant')
                            .replace(/Y/g, 'Y-linked')
                            .replace(/M/g, 'Mitochondrial');
                    }
                    const gens = this.custom.geneSymbol.split(',');
                    this.inputVariantInfo.filter(sit => gens.includes(sit.gene)).forEach(sit => {
                        console.log('custom variantCaseInfo isame->', sit.gene);
                        sit.sameCount = 1;
                        this.custom.sameCount = 1;
                    });
                    this.inputVariantInfo = this.inputVariantInfo.sort((a, b) => a.sameCount > 0 ? -1 : 1);

                    return this.inputInfo.phenotype.split(',').concat(this.custom.phenotypesHpoTerms.split(',')).map(pitem => pitem.trim());
                }),
                mergeMap((it: string[]) => this.api.get<TermsMap>(`/metas/terms`, {params: {ids: it}})),
            )
            .subscribe(termsMap => {
                this.customTerms = new Array<TermDup>();
                this.custom.phenotypesHpoTerms.split(',').forEach(pitem => this.customTerms.push(termsMap[pitem.trim()]));
                this.inputInfoTerms = new Array<TermDup>();
                this.inputInfo.phenotype.split(',').forEach(pitem => {
                    const term = termsMap[pitem.trim()];
                    this.inputInfoTerms.push(term);
                    this.customTerms.filter((sit: TermDup) => term.id === sit.id).forEach(it => {
                        term.dup = true;
                        it.dup = true;
                    });
                });
            }, (e) => {
                debugger;
                this.api.errorHandler(e);
            });

        // this.api.get<InputInfo>(`/patients/results/inputs${this.ptntSeq}`)
        //     .subscribe(data => this.inputInfo = data, this.api.errorHandler.bind(this.api));
        //
        // this.api.get<Custom>(`/evidences/${this.evidenceId}`)
        //     .subscribe(data => this.custom = data, this.api.errorHandler.bind(this.api));
    }

    ngAfterViewInit() {
    }

    ngOnDestroy() {
    }


}
