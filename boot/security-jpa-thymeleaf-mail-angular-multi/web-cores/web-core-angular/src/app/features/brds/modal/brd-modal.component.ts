import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {AuthDetail, Brd, BrdCateCd, UserDetails} from '@web-core-generate/models';
import {JsonApiService, UserService} from '@web-core/app/services';
import {AlertService} from '@web-core/app/services/ui/alert.service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {ValidationService} from '@web-core/app/services/validation/validation.service';
import {of} from 'rxjs/index';
import {zip} from 'rxjs/internal/observable/zip';
import {ValidUtil} from '@web-core/app/utils/ValidUtil';
import {mergeMap} from 'rxjs/internal/operators/mergeMap';

declare let $: any;

@Component({
    selector: 'web-core-brd-modal',
    templateUrl: './brd-modal.component.html',
    styleUrls: ['./brd-modal.component.css'],
})

export class BrdModalComponent implements OnInit {

    @ViewChild('modalTemplate') bbsTemplate;

    @Output() complete = new EventEmitter();

    bsModalRef: BsModalRef;
    formGroup: FormGroup;
    userDetails: UserDetails;
    private brd: Brd = {adm: {}} as Brd;
    private auths: AuthDetail[];
    answer = {} as Brd;

    constructor(private http: HttpClient, private router: Router, private modalService: BsModalService,
                private api: JsonApiService, private userService: UserService,
                private alertService: AlertService, private i18n: I18nService,
                private validationService: ValidationService) {
    }

    ngOnInit() {
        this.formGroup = new FormGroup({
            titl: new FormControl('', [
                Validators.required,
                // Validators.minLength(2),
                Validators.maxLength(100)
            ]),
            regDt: new FormControl('', []),
            admNm: new FormControl('', []),
            answer: new FormControl('', []),
            // titlEn: new FormControl('', [
            //     Validators.maxLength(100)
            // ]),
            // useYn: new FormControl('', [
            //     Validators.required
            // ]),
            cont: new FormControl('', [
                Validators.required,
                Validators.maxLength(4000)
            ]),
            // contEn: new FormControl('', [
            //     Validators.maxLength(4000)
            // ])
        });
    }

    public urlType(cateCd: BrdCateCd = this.brd.cateCd) {
        if (cateCd === 'BCC001') {
            return 'notices';
        } else if (cateCd === 'BCC004') {
            return 'qas';
        }
    }

    public show(brd: Brd) {
        // this.userService.user$.subscribe(it => {
        //     console.log('---', it);
        // });
        // of(Object.assign({}, brd)).subscribe(it => {
        //     console.log('---', it);
        // });

        // this.userService.user$.pipe(
        //     mergeMap(it => {
        //         this.userDetails = it;
        //         return of(Object.assign({}, brd));
        //     })
        // ).subscribe(it => {
        //     this.brd = it;
        //     this.bsModalRef = this.modalService.show(this.bbsTemplate);
        // });
        if (!brd.cateCd) {
            this.alertService.dangerAlert(this.i18n.getTranslation('Error'), 'cateCd ' + this.i18n.getTranslation('Valid_required'));
            throw new Error('cateCd input');
        }
        this.answer = {} as Brd;
        this.answer.prntBrdSeq = brd.brdSeq;
        this.userDetails = undefined;
        this.auths = [];
        let authConfirmUrl = `/brds/${this.urlType(brd.cateCd)}`;
        if (!ValidUtil.isEmpty(brd.brdSeq)) {
            authConfirmUrl += `/${brd.brdSeq}`;
        }
        zip(this.userService.user$, of(Object.assign({}, brd)), this.userService.getAuths(authConfirmUrl)).subscribe((it: [UserDetails, Brd, AuthDetail[]]) => {
            this.userDetails = it[0];
            this.brd = it[1];
            this.auths = it[2];
            this.bsModalRef = this.modalService.show(this.bbsTemplate);
        });
        // forkJoin([this.userService.user$, of(2)]).subscribe(it => {
        //     console.log('--->', it);
        // });
        // this.userService.user$.complete();

        // forkJoin([this.userService.user$, of(Object.assign({}, brd))])
        //     .subscribe((it: [UserDetails, Brd]) => {
        //         this.userDetails = it[0];
        //         this.brd = it[1];
        //         console.log('user', this.userDetails, this.brd);
        //         this.bsModalRef = this.modalService.show(this.bbsTemplate);
        //     });
    }

    isUpdate() {
        const sw = this.auths.filter(it => it.crudTypeCd === 'PUT').length > 0;
        return true === (this.userDetails && this.brd && this.brd.adm && this.userDetails.admSeq === this.brd.adm.admSeq && sw);
    }

    isDelete() {
        const sw = this.auths.filter(it => it.crudTypeCd === 'DELETE').length > 0;
        return true === (this.userDetails && this.brd && this.brd.adm && this.userDetails.admSeq === this.brd.adm.admSeq && sw);
    }

    isRegister() {
        const sw = this.auths.filter(it => it.crudTypeCd === 'POST').length > 0;
        return true === (this.userDetails && this.brd && undefined === this.brd.admSeq && sw);
    }

    isAnswer() {
        const sw = this.auths.filter(it => it.crudTypeCd === 'POST').length > 0;
        return true === (this.userDetails && this.brd && this.brd.adm && this.userDetails.admSeq === this.brd.adm.admSeq && sw);
    }

    private closeAndComplet() {
        this.complete.emit();
        this.close();
    }

    close() {
        this.bsModalRef.hide();
    }

    update() {
        if (this.formGroup.valid) {
            const formData = this.formGroup.value;
            this.api.put<any>(`/brds/${this.urlType(this.brd?.cateCd)}/${this.brd?.brdSeq}`, {params: formData}).subscribe((_) => {
                this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                this.closeAndComplet();
            });
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    register() {
        if (this.formGroup.valid) {
            const formData = this.formGroup.value;
            this.api.post<any>(`/brds/${this.urlType(this.brd?.cateCd)}`, {params: formData}).subscribe((_) => {
                this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                this.closeAndComplet();
            });
        } else {
            this.validationService.validCheck(this.formGroup);
        }
    }

    delete() {
        this.api.delete(`/brds/${this.urlType(this.brd?.cateCd)}/${this.brd?.brdSeq}`).subscribe(it => {
            this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
            this.closeAndComplet();
        }, this.api.errorHandler.bind(this.api));
    }

    registerAnswer() {
        if (this.answer.cont) {
            this.api.post<any>(`/brds/${this.urlType(this.brd?.cateCd)}`, {params: this.answer})
                .pipe(
                    mergeMap(it => this.api.get<Brd>(`/brds/${this.urlType(this.brd?.cateCd)}/${this.answer.prntBrdSeq}`))
                ).subscribe((brd) => {
                this.brd = brd;
                this.answer.cont = '';
                this.alertService.successAlert(this.i18n.getTranslation('word.success'), this.i18n.getTranslation('msg.success'));
                this.complete.emit();
            });
        } else {
            this.alertService.dangerAlert(this.i18n.getTranslation('Answer'), this.i18n.getTranslation('Valid_required'));
        }
    }

}
