import {NgModule} from '@angular/core';

import {WebCoreDatepickerDirective} from '@web-core-app/directives/form/input/webCoreDatepicker.directive';
// import {BootstrapModule} from "@app/shared/bootstrap.module";
import {BootstrapModule} from "@web-core-app/shareds/bootstrap.module";
// @NgModule()
// class BB extends BootstrapModule {}
@NgModule({
    imports: [
        BootstrapModule
    ],
    declarations: [
        WebCoreDatepickerDirective
    ],
    exports: [
        WebCoreDatepickerDirective,
        BootstrapModule
    ]
})

export class SharedModule {
}
