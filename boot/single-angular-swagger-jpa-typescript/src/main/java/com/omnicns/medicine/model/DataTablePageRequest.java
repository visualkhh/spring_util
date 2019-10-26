package com.omnicns.medicine.model;

import lombok.*;
import org.springframework.data.domain.*;

import java.io.Serializable;
@Deprecated
@Getter @Setter @EqualsAndHashCode(callSuper = false) @AllArgsConstructor @NoArgsConstructor
public class DataTablePageRequest implements Pageable, Serializable {

    private Integer page;
    private Integer size;
    private Sort sort;
    private Integer draw;
//    private String search;

    public DataTablePageRequest(Integer page, Integer size) {
        this(page,size,0);
    }
    public DataTablePageRequest(Integer page, Integer size, Integer draw) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.page = page;
        this.size = size;
        this.draw = draw;
    }

    public DataTablePageRequest(Integer page, Integer size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }
    public DataTablePageRequest(Integer page, Integer size, Sort sort) {
        this(page, size);
        this.sort = sort;
    }

    public Sort getSort() {
        return sort;
    }






    public int getPageSize() {
        return size;
    }

    public int getPageNumber() {
        return page;
    }

    public int getOffset() {
        return page * size;
    }

    public boolean hasPrevious() {
        return page > 0;
    }

    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }


    public Pageable next() {
        return new PageRequest(getPageNumber() + 1, getPageSize(), getSort());
    }
    public DataTablePageRequest previous() {
        return getPageNumber() == 0 ? this : new DataTablePageRequest(getPageNumber() - 1, getPageSize(), getSort());
    }

    public Pageable first() {
        return new PageRequest(0, getPageSize(), getSort());
    }


    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, draw %d, sort: %s]", getPageNumber(), getPageSize(), getDraw(), sort == null ? null : sort.toString());
    }
}
