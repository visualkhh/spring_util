import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, NavigationError, NavigationStart, Router, RouterEvent} from '@angular/router';

// import { routerTransition } from "@app/shared/utils/animations";


export enum viewtype {
    strip = 'strip'
}
@Component({
    selector: 'app-main-layout',
    templateUrl: './main-layout.component.html',
    styles: [],
    // animations: [routerTransition]
})
export class MainLayoutComponent implements OnInit {

    public headerVisible = true;
    public navigationVisible = true;
    public footerVisible = true;
    constructor(private router: Router, private activatedRoute: ActivatedRoute) {
        // console.log('mainLayout', this.activatedRoute.url, this.activatedRoute.snapshot.paramMap);
        this.router.events.subscribe((event: RouterEvent) => {
            // console.log('mainLayout Event:', event);
            // console.log('-->', this.activatedRoute.snapshot.paramMap)
            if (event instanceof NavigationStart) {
                // Show loading indicator
            }

            if (event instanceof NavigationEnd) {
                // Hide loading indicator
                const regExp: RegExp = /[?&]+([^=&]+)=([^&]*)/gi;
                const queryParams: {[name: string]: string} = {};
                event.url.replace(regExp, (str: string, key: string, value: string) => {queryParams[key] = value; return str; });
                if (viewtype.strip === queryParams.viewtype) {
                    this.strp();
                } else {
                    this.unStrip();
                }
                // console.log('headerVisible', this.headerVisible, 'navigationVisible', this.navigationVisible, 'footerVisible', this.footerVisible);
            }

            if (event instanceof NavigationError) {
                console.log(event.error);
            }
        });
    }

    ngOnInit() {

    }

    strp() {
        this.headerVisible = this.navigationVisible = this.footerVisible = false;
    }
    unStrip() {
        this.headerVisible = this.navigationVisible = this.footerVisible = true;
    }
    getState(outlet) {
        if (!outlet.activatedRoute) {
            return;
        }
        const ss = outlet.activatedRoute.snapshot;

        // return unique string that is used as state identifier in router animation
        return (
            outlet.activatedRouteData.state ||
            (ss.url.length
                ? ss.url[0].path
                : ss.parent.url.length
                    ? ss.parent.url[0].path
                    : null)
        );
    }
}

