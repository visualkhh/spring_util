import {
  Component,
  Input,
  ElementRef,
  AfterContentInit,
  OnInit, OnChanges, SimpleChanges, AfterContentChecked, Output, EventEmitter, ViewChild
} from '@angular/core';
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/dataTables.buttons.min.js";
import 'script-loader!jszip/dist/jszip.min.js';
// import "script-loader!smartadmin-plugins/datatables/datatables-bundle.min.js";
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/buttons.html5.min.js";
import {I18nService, JsonApiService, UserService} from '@app/services';
import {AuthDepthDetail, UseCd, CrudTypeCd} from '@generate/models';
import {Page} from '@app/models/Page';
import {PageRequest} from '@app/models/PageRequest';
import {DatatableComponent} from '@app/features/datatable/datatable.component';
import { EMPTY } from 'rxjs';
import {pipe} from 'rxjs/internal/util/pipe';
import {timeout} from 'rxjs/internal/operators/timeout';
import {map} from 'rxjs/internal/operators/map';
import {of} from 'rxjs/internal/observable/of';
import {delay} from 'rxjs/internal/operators/delay';
// import "script-loader!smartadmin-plugins/datatables-bundle/datatables.min.js";
declare let $: any;
declare let moment: any;

export type Button = {
  link?: string;
  text: string;
  class?: string;
};

@Component({
  selector: 'inline-navigation[url]',
  templateUrl: 'inline-navigation.component.html',
  styleUrls: ['inline-navigation.component.css']
})
export class InlineNavigationComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
  @Input() class = '';
  @Input() url = '';
  @Input() buttons: Button[] = [];

  private authDetailDepthMergeUrlDedups: AuthDepthDetail[];
  constructor(private el: ElementRef, private i18n: I18nService, private userService: UserService, private api: JsonApiService) {}

  ngOnInit() {
    this.userService.user$.subscribe(it => {
      // console.log('user subscribe', it);
      this.authDetailDepthMergeUrlDedups = [];
      const data = this.userService.authDupDepthDetailsLoopFilter(
          sit => CrudTypeCd.GET === sit.crudTypeCd && (sit.url === this.url || (UseCd.USE001 === sit.regexpCd && RegExp(sit.url).test(this.url))),
          sit => this.authDetailDepthMergeUrlDedups.push(sit)
      );
      this.authDetailDepthMergeUrlDedups = this.authDetailDepthMergeUrlDedups.reverse();
      // console.log('--->', this.authDetailDepthMergeUrlDedups);
    });

    // of(1).pipe(
    //     delay(5000),
    //     map((_) => 1)
    // ).subscribe(it => {
    //   console.log('-----', it);
    //   this.userService.reloadUserDetails();
    // });
  }
  public getPaths() {

  }


  ngOnChanges(changes: SimpleChanges) {
    // console.log('-DatatableComponent--ngOnChanges-')
  }

  ngAfterContentInit(): void {
    // console.log('-DatatableComponent--ngAfterContentInit-')
  }

  ngAfterContentChecked(): void {
    // console.log('-DatatableComponent--ngAfterContentChecked-')
  }

}
