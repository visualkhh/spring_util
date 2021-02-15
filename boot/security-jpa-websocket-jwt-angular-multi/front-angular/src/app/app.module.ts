import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {LayoutModule} from '@app/shareds/layout/layout.module';
import {AppRoutingModule} from './app-routing.module';
import {ServiceModule} from '@app/services/service.module';
import {SharedModule} from '@app/shareds/shared.module';
import {
    InjectableRxStompConfig,
    RxStompService,
    rxStompServiceFactory,
    StompConfig,
    StompService
} from '@stomp/ng2-stompjs';
import {myRxStompConfig} from '@app/my-rx-stomp.config';

/*
declarations - 이 모듈에서 사용하는 뷰 클래스를 정의한다. Angular에는 컴포넌트, 디렉티브, 파이프 세 종류의 뷰 클래스가 있다.
exports - 다른 모듈이나 컴포넌트 템플릿에서 접근할 수 있도록 외부로 공개 선언한다.
import - export로 공개된 클래스를 다른 컴포넌트 템플릿의 this 모듈에 선언해서 사용할 때 사용한다.
providers - 전역에서 사용되는 서비스를 해당 객체에서 사용할 수 있도록 생성하고 지정한다. 프로바이더는 앱의 모든 곳에서 접근할 수 있다.
bootstrap - 루트 컴포넌트라고 하는 메인 어플리케이션의 뷰를 선언한다. bootstrap 항목은 루트 모듈에만 존재한다.
 */

@NgModule({
    imports: [
        BrowserModule,
        AppRoutingModule,
        SharedModule,
        LayoutModule,
        ServiceModule,
    ],
    declarations: [
        AppComponent,
    ],
    providers: [
        // StompService,
        // {
        //     provide: StompConfig,
        //     useValue: myRxStompConfig
        // }
        {
            provide: InjectableRxStompConfig,
            useValue: myRxStompConfig,
        },
        {
            provide: RxStompService,
            useFactory: rxStompServiceFactory,
            deps: [InjectableRxStompConfig],
        }
    ],
    exports: [
        BrowserModule,
        AppRoutingModule,
        SharedModule,
        LayoutModule,
        ServiceModule
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
