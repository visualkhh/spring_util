import {Pageable} from '@web-core/app/models/Pageable';

export class PageRequest extends Pageable {


    constructor(size: number, page: number, draw: number) {
        super();
        this.size = size;
        this.page = page;
        this.draw = draw;
    }
}
