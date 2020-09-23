import {NgModule} from '@angular/core';
import {BrdDatatableModule} from '@web-core-app/features/brds/datatable/brd.datatable.module';
import {UserDetailsModule} from '@web-core-app/features/auths/user-details/user-details.module';
import {InlineNavigationModule} from '@web-core-app/features/navigation/inline-navigation/inline-navigation.module';
import {ProjectService} from '@app/services/project.service';
import {QuillModule} from 'ngx-quill';
// import {WebCoreDatepickerDirective} from '@web-core-app/directives/form/input/webCoreDatepicker.directive';
// import {Camera, FileText, Github, Heart, Save, Settings, Plus, PlusCircle, PlusSquare, User, Info} from 'angular-feather/icons';
// // import {BootstrapModule} from "@app/shared/bootstrap.module";
// import {I18nPipe} from '@web-core-app/pipes/i18n/i18n.pipe';
// import {FeatherModule} from 'angular-feather';
// import {ModalModule} from 'ngx-bootstrap/modal';
// import {ButtonsModule} from 'ngx-bootstrap/buttons';
// import {TooltipModule} from 'ngx-bootstrap/tooltip';
// import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
// import {ProgressbarModule} from 'ngx-bootstrap/progressbar';
// import {AlertModule} from 'ngx-bootstrap/alert';
// import {TabsModule} from 'ngx-bootstrap/tabs';
// import {AccordionModule} from 'ngx-bootstrap/accordion';
// import {CarouselModule} from 'ngx-bootstrap/carousel';
// import {CommonModule} from '@angular/common';
// @NgModule()
// class BB extends BootstrapModule {}

const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],

    [{ header: 1 }, { header: 2 }],               // custom button values
    [{ list: 'ordered'}, { list: 'bullet' }],
    [{ script: 'sub'}, { script: 'super' }],      // superscript/subscript
    [{ indent: '-1'}, { indent: '+1' }],          // outdent/indent
    [{ direction: 'rtl' }],                         // text direction

    [{ size: ['small', false, 'large', 'huge'] }],  // custom dropdown
    [{ header: [1, 2, 3, 4, 5, 6, false] }],

    [{ color: [] }, { background: [] }],          // dropdown with defaults from theme
    [{ font: [] }],
    [{ align: [] }],

    ['clean']                                         // remove formatting button
];

// component는 사용하는 모듈쪽에서만 가져다가 써라.
@NgModule({
    imports: [
        // BrdDatatableModule,
        InlineNavigationModule,
        QuillModule.forRoot(
            {
                modules: {
                    // syntax: true,
                    toolbar: toolbarOptions
                }
            }
        )
    ],
    declarations: [],
    providers: [ProjectService],
    exports: [
        // BrdDatatableModule,
        InlineNavigationModule,
        QuillModule
    ]
})

export class SharedModule {
}
