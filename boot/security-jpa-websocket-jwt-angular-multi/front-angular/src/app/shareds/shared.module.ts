import {NgModule} from '@angular/core';
import {ProjectService} from '@app/services/project.service';
import {QuillModule} from 'ngx-quill';
import {DatepickerDirective} from '@app/shareds/directives/form/input/datepicker.directive';
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
    Package,
    Plus,
    PlusCircle,
    PlusSquare,
    Save,
    Settings,
    User,
    Twitch,
    Twitter,
    UserPlus,
    FilePlus,
    FolderPlus,
    RefreshCcw,
    RefreshCw
} from 'angular-feather/icons';
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
import {BootstrapModule} from '@app/shareds/bootstrap.module';
import {MomentPipe} from '@app/shareds/pipes/date/moment.pipe';
import {BootstrapTreeviewComponent} from '@app/shareds/tree/bootstrap-treeview.component';
import {SplitPipe} from '@app/shareds/pipes/string/split.pipe';
import {BasicModalComponent} from '@app/shareds/modals/basic-modal/basic-modal.component';
import {SignOutComponent} from '@app/features/auths/signs/sign-out/sign-out.component';

const toolbarOptions = [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote', 'code-block'],

    [{header: 1}, {header: 2}],               // custom button values
    [{list: 'ordered'}, {list: 'bullet'}],
    [{script: 'sub'}, {script: 'super'}],      // superscript/subscript
    [{indent: '-1'}, {indent: '+1'}],          // outdent/indent
    [{direction: 'rtl'}],                         // text direction

    [{size: ['small', false, 'large', 'huge']}],  // custom dropdown
    [{header: [1, 2, 3, 4, 5, 6, false]}],

    [{color: []}, {background: []}],          // dropdown with defaults from theme
    [{font: []}],
    [{align: []}],

    ['clean']                                         // remove formatting button
];

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
            AlignCenter,
            AlignJustify,
            AlignLeft,
            AlignRight,
            Package,
            Twitch,
            Twitter,
            UserPlus,
            FilePlus,
            FolderPlus,
            RefreshCcw,
            RefreshCw
        }),
        FormsModule,
        ReactiveFormsModule,
        QuillModule,
        QuillModule.forRoot(
            {
                modules: {
                    // syntax: true,
                    toolbar: toolbarOptions
                }
            }
        )
    ],
    declarations: [
        DatepickerDirective,
        MomentPipe,
        SplitPipe,
        BasicModalComponent,
        BootstrapTreeviewComponent,
        SignOutComponent
    ],
    providers: [ProjectService, MomentPipe],
    exports: [
        DatepickerDirective,
        BootstrapModule,
        MomentPipe,
        SplitPipe,
        FeatherModule,
        FormsModule,
        ReactiveFormsModule,
        BasicModalComponent,
        BootstrapTreeviewComponent,
        QuillModule,
        SignOutComponent
    ]
})

export class SharedModule {
}
