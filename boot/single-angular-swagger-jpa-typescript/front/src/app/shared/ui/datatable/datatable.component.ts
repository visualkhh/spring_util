import {
  Component,
  Input,
  ElementRef,
  AfterContentInit,
  OnInit, OnChanges, SimpleChanges, AfterContentChecked, Output, EventEmitter
} from "@angular/core";
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/dataTables.buttons.min.js";
import "script-loader!jszip/dist/jszip.min.js";
// import "script-loader!smartadmin-plugins/datatables/datatables-bundle.min.js";
// import "script-loader!smartadmin-plugins/datatables/Buttons-1.2.4/js/buttons.html5.min.js";
import "script-loader!smartadmin-plugins/datatables/datatables.min.js";
import "script-loader!datatables.net-fixedcolumns/js/dataTables.fixedColumns.min.js";
import "script-loader!datatables.net-fixedheader/js/dataTables.fixedHeader.min.js";
// import "script-loader!smartadmin-plugins/datatables-bundle/datatables.min.js";

@Component({
  selector: "sa-datatable",
  template: `
      <table class="dataTable responsive {{tableClass}}" width="{{width}}">
        <ng-content (contentChange)="doSomething()"></ng-content>
      </table>
`,
  // require("smartadmin-plugins/datatables/Buttons-1.2.4/css/buttons..min.css"),
  styles: [
    require("smartadmin-plugins/datatables/datatables.min.css"),
  ]
})
export class DatatableComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
  @Input() public options: any;
  @Input() public filter: any;
  @Input() public detailsFormat: any;

  @Input() public paginationLength: boolean;
  @Input() public columnsHide: boolean;
  @Output() destroyCompleted = new EventEmitter<any>();
  @Input() public tableClass: string;
  @Input() public width: string = "100%";
  private _dataTable: any;
  private tableHeaderElement: any;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    // alert(this.el.nativeElement.innerHTML)
    // if (this.options['autoRender'] === undefined) {
    //   this.options['autoRender'] = true;
    // }
    // if (this.options['autoRender']){
    //   this.render();
    // }
      this.render();
  }
  doSomething() {
    // console.log('-DatatableComponent--doSomething-')
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
  render() {

    if (this. options.targetContainerSelctor) {
      $(this.el.nativeElement).empty();
      // alert($(this.options.targetContainerSelctor).html());
      let clone = $(this.options.targetContainerSelctor).clone();
      $(this.el.nativeElement).append(clone);
      clone.show();
    }
      this.tableHeaderElement = $(this.el.nativeElement.children[0]);

    let options = this.options || {};
    let toolbar = "";
    if (options.buttons) toolbar += "B";
    if (this.paginationLength) toolbar += "l";
    if (this.columnsHide) toolbar += "C";

    if (typeof options.ajax === "string") {
      let url = options.ajax;
      options.ajax = {
        url: url
        // complete: function (xhr) {
        //
        // }
      };
    }

    //Bfrtip
    let dom = options.dom;
    options = $.extend(options, {
      // dom:
      //   "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs text-right'" +
      //   toolbar +
      //   ">r>" +
      //   "t" +
      //   "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
      dom:
        "<'dt-toolbar'<'col-xs-12 col-sm-6'><'col-sm-6 col-xs-12 hidden-xs text-right'" +
        toolbar + "" +
        ">r>" +
        "t" +
        "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
      oLanguage: {
        sSearch:
          "<span class='input-group-addon'><i class='glyphicon glyphicon-search'></i></span> ",
        sLengthMenu: "_MENU_"
      },
      autoWidth: false,
      retrieve: true,
      responsive: true,
      initComplete: (settings, json) => {
        this.tableHeaderElement
          .parent()
          .find(".input-sm")
          .removeClass("input-sm")
          .addClass("input-md");
      }
    });

    // if (dom) {
    //   options.dom = dom;
    // }

    this._dataTable = this.tableHeaderElement.DataTable(options);

    if (this.filter) {
      // Apply the filter
      this.tableHeaderElement.on("keyup change", "thead th input[type=text]", function() {
        this._dataTable
          .column(
            $(this)
              .parent()
              .index() + ":visible"
          )
          .search(this.value)
          .draw();
      });
    }

    if (!toolbar) {
      this.tableHeaderElement
        .parent()
        .find(".dt-toolbar")
        .append(
          '<div class="text-right"><img src="assets/img/logo.gif" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>'
        );
    }

    if (this.detailsFormat) {
      let format = this.detailsFormat;
      this.tableHeaderElement.on("click", "td.details-control", function() {
        var tr = $(this).closest("tr");
        var row = this._dataTable.row(tr);
        if (row.child.isShown()) {
          row.child.hide();
          tr.removeClass("shown");
        } else {
          row.child(format(row.data())).show();
          tr.addClass("shown");
        }
      });
    }
  }

  public getDataTable() {
    return this._dataTable;
  }
  public destroy() {
    this._dataTable.destroy();
    $(this.el.nativeElement).empty();
    this.destroyCompleted.emit();

  }
  public draw(){
   this._dataTable.draw();
  }


}
