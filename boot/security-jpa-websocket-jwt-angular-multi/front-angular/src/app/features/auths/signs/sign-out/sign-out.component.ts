import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {UserService} from '@app/services/user.service';
import {CookieService} from 'ngx-cookie-service';

@Component({
    selector: 'sign-out',
    styleUrls: ['sign-out.component.css'],
    templateUrl: './sign-out.component.html',
})
export class SignOutComponent implements OnInit {

    @ViewChild('modalTemplate') modalTemplate;
    @ViewChild('from') form: ElementRef;
    @Input() public class = '';

    bsModalRef: BsModalRef;
    config = {
        // class: 'modal-dialog-centered',
        // class: 'modal-dialog-centered modal-sm',
        class: 'modal-sm',
        ignoreBackdropClick: false
    };

    constructor(private userService: UserService, private modalService: BsModalService, private cookieService: CookieService) {

    }

    ngOnInit() {
    }

    close() {
        this.bsModalRef.hide();
    }

    yes() {
        this.userService.logout();
        const form: HTMLFormElement = this.form.nativeElement;
        form.submit();
    }

    no() {
        this.close();
    }

    show() {
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
    }
}
