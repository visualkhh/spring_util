import {Sort} from '@app/models/Sort';
import {Pageable} from '@app/models/Pageable';

export class Page<T> {
    content: T[];
    pageable: Pageable;
    last: boolean;
    totalPages: number;
    totalElements: number;
    sort: Sort;
    first: boolean;
    numberOfElements: number;
    size: number;
    number: number;
    empty: boolean;

    get page() {
        // return this.number;
        return this.totalPages;
    }
    get start() {
        return this.number * this.size;
    }
    get end() {
        return (this.number * this.size) + this.size;
    }
    // 요청한 개수(size) 그대로 돌려줌
    get length() {
        return this.size;
    }

    get recordsTotal() {
        return this.totalElements;
    }

    get recordsDisplay() {
        return this.totalElements;
    }

    get recordsFiltered() {
        return this.totalElements;
    }

    get total() {
        return this.totalElements;
    }

    get data() {
        return this.content;
    }



}
