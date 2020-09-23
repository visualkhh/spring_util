import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RouterModule} from '@angular/router';
import {LanguageSelectorComponent} from '@web-core/app/features/i18n/language-selector/language-selector.component';
import {UserDetailsComponent} from '@web-core/app/features/auths/user-details/user-details.component';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import {I18nPipe} from '@web-core/app/pipes/i18n/i18n.pipe';
import {SharedModule} from '@web-core-app//shareds/shared.module';
import {services} from '@web-core/app/services';
import {HistoryDatatableComponent} from '@web-core/app/features/auths/history/datatable/history.datatable.component';
/*
declarations - 이 모듈에서 사용하는 뷰 클래스를 정의한다. Angular에는 컴포넌트, 디렉티브, 파이프 세 종류의 뷰 클래스가 있다.
exports - 다른 모듈이나 컴포넌트 템플릿에서 접근할 수 있도록 외부로 공개 선언한다.
import - export로 공개된 클래스를 다른 컴포넌트 템플릿의 this 모듈에 선언해서 사용할 때 사용한다.
providers - 전역에서 사용되는 서비스를 해당 객체에서 사용할 수 있도록 생성하고 지정한다. 프로바이더는 앱의 모든 곳에서 접근할 수 있다.
bootstrap - 루트 컴포넌트라고 하는 메인 어플리케이션의 뷰를 선언한다. bootstrap 항목은 루트 모듈에만 존재한다.
 */

@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        SharedModule,
    ],
    providers: [
    ],
    declarations: [HistoryDatatableComponent],
    exports: [
        HistoryDatatableComponent,

    ]
})
export class HistoryDatatableModule {
}
