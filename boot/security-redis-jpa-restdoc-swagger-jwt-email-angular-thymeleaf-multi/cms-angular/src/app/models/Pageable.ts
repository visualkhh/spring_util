import {Sort} from '@app/models/Sort';


export class Pageable {
    size: number;
    page: number;
    draw: number;

    sort: Sort;
    pageSize: number;
    pageNumber: number;
    offset: number;
    unpaged: boolean;
    paged: boolean;
}
