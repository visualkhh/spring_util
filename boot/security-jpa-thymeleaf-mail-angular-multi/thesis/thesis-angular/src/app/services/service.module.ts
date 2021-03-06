import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from "@angular/common";
import {HttpClientModule} from "@angular/common/http";
import {services} from "@web-core-app/services/index";
// import {AlertService} from "@web-core-app/services/ui/alert.service";

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule,
    ],
    providers: [
        ...services
    ]
})
export class ServiceModule {
    constructor(@Optional() @SkipSelf() parentModule: ServiceModule) {
        //throwIfAlreadyLoaded(parentModule, 'CoreModule');
    }

}
