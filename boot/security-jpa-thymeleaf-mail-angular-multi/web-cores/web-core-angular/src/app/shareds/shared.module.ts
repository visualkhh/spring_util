import {NgModule} from '@angular/core';
import {DatepickerDirective} from '@web-core-app/directives/form/input/datepicker.directive';
import {
    AlignCenter,
    AlignJustify,
    AlignLeft,
    AlignRight,
    Book,
    Camera,
    FileText,
    Github,
    Globe,
    Grid,
    Heart,
    HelpCircle,
    Info,
    Plus,
    PlusCircle,
    PlusSquare,
    Save,
    Settings,
    User,
    Package
} from 'angular-feather/icons';
// import {BootstrapModule} from "@app/shared/bootstrap.module";
import {I18nPipe} from '@web-core-app/pipes/i18n/i18n.pipe';
import {FeatherModule} from 'angular-feather';
import {ModalModule} from 'ngx-bootstrap/modal';
import {ButtonsModule} from 'ngx-bootstrap/buttons';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ProgressbarModule} from 'ngx-bootstrap/progressbar';
import {AlertModule} from 'ngx-bootstrap/alert';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {CarouselModule} from 'ngx-bootstrap/carousel';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BootstrapModule} from '@web-core/app/shareds/bootstrap.module';
import {MomentPipe} from '@web-core/app/pipes/date/moment.pipe';
import {DatatableComponent} from '@web-core/app/features/datatable/datatable.component';
import {BootstrapTreeviewComponent} from '@web-core/app/features/tree/bootstrap-treeview.component';
import {SplitPipe} from '@web-core/app/pipes/string/split.pipe';
import {BasicModalComponent} from '@web-core/app/features/modals/basic-modal/basic-modal.component';
import {BrdModalComponent} from '@web-core-app/features/brds/modal/brd-modal.component';
import {UserDetailsComponent} from '@web-core-app/features/auths/user-details/user-details.component';
import {QuillModule} from 'ngx-quill';
import {HistoryDatatableComponent} from '@web-core/app/features/auths/history/datatable/history.datatable.component';
import {BrdDatatableComponent} from '@web-core/app/features/brds/datatable/brd.datatable.component';
import {PoliciesConfirmModalComponent} from '@web-core/app/features/auths/signs/policies-confirm-modal/policies-confirm-modal.component';
import {DiseaseModalComponent} from '@web-core/app/features/patients/modal/disease/disease-modal.component';
import {PatientDatatableComponent} from '@web-core/app/features/patients/datatable/patient.datatable.component';
import {AdmDatatableComponent} from '@web-core/app/features/setups/adms/datatable/adm.datatable.component';
import {AdmModalComponent} from '@web-core/app/features/setups/adms/modal/adm-modal.component';
import {CorpDatatableComponent} from "@web-core/app/features/setups/corps/datatable/corp.datatable.component";
import {CorpModalComponent} from "@web-core/app/features/setups/corps/modal/corp-modal.component";
import {SignUpModalComponent} from "@web-core/app/features/auths/signs/sign-up/modal/sign-up-modal.component";
import {IdpwdFindModalComponent} from "@web-core/app/features/auths/find/modal/idpwd-find-modal.component";
// @NgModule()
// class BB extends BootstrapModule {}

// component는 사용하는 모듈쪽에서만 가져다가 써라.
@NgModule({
    imports: [
        CommonModule,
        BootstrapModule,
        ModalModule.forRoot(),
        ButtonsModule.forRoot(),
        TooltipModule.forRoot(),
        BsDropdownModule.forRoot(),
        ProgressbarModule.forRoot(),
        AlertModule.forRoot(),
        TabsModule.forRoot(),
        AccordionModule.forRoot(),
        CarouselModule.forRoot(),
        FeatherModule.pick({
            Camera,
            FileText,
            Save,
            Settings,
            Heart,
            Github,
            Plus,
            PlusCircle,
            PlusSquare,
            User,
            Info,
            HelpCircle,
            Globe,
            Grid,
            Book,
            AlignCenter, AlignJustify, AlignLeft, AlignRight,
            Package
        }),
        FormsModule,
        ReactiveFormsModule,
        QuillModule,
    ],
    declarations: [
        DatepickerDirective,
        I18nPipe,
        MomentPipe,
        SplitPipe,
        DatatableComponent,
        BasicModalComponent,
        BrdModalComponent,
        HistoryDatatableComponent,
        UserDetailsComponent,
        DiseaseModalComponent,
        BrdDatatableComponent,
        PoliciesConfirmModalComponent,
        PatientDatatableComponent,
        AdmDatatableComponent,
        AdmModalComponent,
        CorpDatatableComponent,
        CorpModalComponent,
        SignUpModalComponent,
        IdpwdFindModalComponent,
        BootstrapTreeviewComponent
    ],
    providers: [I18nPipe, MomentPipe],
    exports: [
        DatepickerDirective,
        BootstrapModule,
        I18nPipe,
        MomentPipe,
        SplitPipe,
        FeatherModule,
        FormsModule,
        ReactiveFormsModule,
        DatatableComponent,
        BasicModalComponent,
        PatientDatatableComponent,
        BrdModalComponent,
        HistoryDatatableComponent,
        UserDetailsComponent,
        DiseaseModalComponent,
        BrdDatatableComponent,
        PoliciesConfirmModalComponent,
        AdmDatatableComponent,
        AdmModalComponent,
        CorpDatatableComponent,
        CorpModalComponent,
        SignUpModalComponent,
        IdpwdFindModalComponent,
        BootstrapTreeviewComponent
    ]
})


export class SharedModule {
}
