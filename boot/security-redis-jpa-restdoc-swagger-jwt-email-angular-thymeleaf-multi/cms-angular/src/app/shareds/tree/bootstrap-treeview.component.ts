import {
    AfterContentChecked,
    AfterContentInit,
    Component,
    ElementRef,
    EventEmitter,
    Input,
    OnChanges,
    OnInit,
    Output,
    SimpleChanges,
    ViewChild
} from '@angular/core';
import '@libs/bootstrap-viewtree/bootstrap-treeview.min.js';

declare var $: any;

export interface TreeNode<T> {
    nodeId: number;
    parentId: number;
    text: string;
    nodes: TreeNode<T>[] & T;
    selectable: boolean;
    state: { checked: boolean, disabled: boolean, expanded: boolean, selected: boolean };
}

export interface TreeEventNode<T> {
    event: any;
    node: TreeNode<T> & T;
}

export interface TreeEventNodeList<T> {
    event: any;
    nodes: (TreeNode<T> & T)[];
}

@Component({
    selector: 'web-core-bootstrap-treeview',
    styleUrls: ['./bootstrap-treeview.component.css'],
    template: ''
})
// angular input variable change detection
// https://stackoverflow.com/questions/38571812/how-to-detect-when-an-input-value-changes-in-angular
export class BootstrapTreeviewComponent implements OnInit, OnChanges, AfterContentInit, AfterContentChecked {
    // @ViewChild('treeview') treeview: ElementRef;
    @Output() checked = new EventEmitter<any>();
    @Output() unChecked = new EventEmitter<any>();
    @Output() selected = new EventEmitter<any>();
    @Output() unSelected = new EventEmitter<any>();
    @Output() renderComplet = new EventEmitter<any>();
    @Input() public class: string;
    @Input() public data: any[];

    private treeviewObject: any;

    constructor(private el: ElementRef) {
    }

    ngOnInit() {
        this.render();
    }

    // change input param
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
        this.treeviewObject = $(this.el.nativeElement).treeview({
            // showIcon: true,
            levels: 1,
            expandIcon: 'fa fa-angle-down',
            // collapseIcon: 'fa fa-angle-right',
            collapseIcon: 'fa fa-caret-right',
            checkedIcon: 'fa fa-check-square-o',
            uncheckedIcon: 'fa fa-square-o',
            showIcon: false,
            showCheckbox: true,
            // backColor: 'green',
            data: this.data,
            // data: defaultData,
            onNodeSelected: (event, node: TreeNode<any>) => {
                this.selected.emit({event, node});
            },
            onNodeUnSelected: (event, node: TreeNode<any>) => {
                this.unSelected.emit({event, node});
            },
            onNodeChecked: (event, node: TreeNode<any>) => {
                this.checked.emit({event, node});
            },
            onNodeUnchecked: (event, node: TreeNode<any>) => {
                this.unChecked.emit({event, node});
            },
            onRenderComplet: (event, ...nodes: TreeNode<any>[]) => {
                setTimeout(() => {
                    this.renderComplet.emit({event, nodes});
                }, 1);
            }
        });
        // console.log('---render complet');
        // this.renderComplet.emit();
    }

    public search(value: string): TreeNode<any>[] {
        if (value) {
            const searchResult = this.treeviewObject.treeview('search', [value, {
                ignoreCase: false,
                exactMatch: false
            }]);
            return searchResult;
        }
    }

    public expandAll() {
        this.treeviewObject.treeview('expandAll', {levels: 5, silent: false});
    }

    public collapseAll() {
        this.treeviewObject.treeview('collapseAll', {levels: 1, silent: false});
    }

    public clearSearch() {
        this.treeviewObject.treeview('clearSearch');
    }

    public getNodes<T>(): TreeNode<T>[] {
        return this.treeviewObject.treeview('getNodes');
    }

    checkNode(treeNodes: TreeNode<any>[]) {
        this.treeviewObject.treeview('checkNode', [treeNodes.map(it => it.nodeId), {silent: false}]);
    }

    uncheckNode(treeNodes: TreeNode<any>[]) {
        this.treeviewObject.treeview('uncheckNode', [treeNodes.map(it => it.nodeId), {silent: false}]);
    }

    selectNode(treeNodes: TreeNode<any>[]) {
        this.treeviewObject.treeview('selectNode', [treeNodes.map(it => it.nodeId), {silent: false}]);
    }

    revealNode(treeNodes: TreeNode<any>[]) {
        // this.treeviewObject.treeview('expandNode', [treeNodes.map(it => it.nodeId), {silent: false}]);
        // console.log('-->', treeNodes);
        // this.treeviewObject.treeview('expandNode', [122, { levels: 2 }]);
        this.treeviewObject.treeview('revealNode', treeNodes);
        // this.treeviewObject.treeview('expandNode', treeNodes[0]);
        treeNodes.map(it => it.nodeId).forEach(it => {
            this.treeviewObject.treeview('revealNode', it);
        });
    }
}
