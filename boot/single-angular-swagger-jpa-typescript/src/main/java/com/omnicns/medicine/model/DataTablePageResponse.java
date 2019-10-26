package com.omnicns.medicine.model;

import com.omnicns.java.reflection.ReflectionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Slf4j
public class DataTablePageResponse<T> implements Page<T> {


    private Pageable pageable;
//    private List<T> content = new ArrayList<>();
    private List<T> data = new ArrayList<>();
    private Boolean first;
    private Boolean last;
    private Integer number;
    private Integer numberOfElements;
    private Integer size;
    private Long recordsFiltered;
    private Integer recordsTotal;
    private Long total;
    private Sort sort;
    public DataTablePageResponse(Page<T> page) {
        if (null == page) {
            return;
        }
        setData(page.getContent());
        setRecordsFiltered(page.getTotalElements());
        setTotal(page.getTotalElements());
        setRecordsTotal(page.getTotalPages());
        setNumber(page.getNumber());
        setNumberOfElements(page.getNumberOfElements());
        setSize(page.getSize());
        setSort(page.getSort());
        setFirst(page.isFirst());
        setFirst(page.isLast());

        if(PageImpl.class.isAssignableFrom(page.getClass())){
            try {
                Pageable selectedPageable = ReflectionUtil.getDeclaredField(((PageImpl)page),"pageable",Pageable.class);
                setPageable(selectedPageable);
            } catch (Exception e) {
                log.error("PageImpl", e);
            }
//            ((PageImpl)page).get
        }

//        this.setPageable();
    }
    /**
     * Constructor of {@code PageImpl}.
     *
     * @param content  the content of this page, must not be {@literal null}.
     * @param pageable the paging information, can be {@literal null}.
     * @param total    the total amount of items available. The total might be adapted considering the length of the content
     *                 given, if it is going to be the content of the last page. This is in place to mitigate inconsistencies
     */
    public DataTablePageResponse(List<T> content, Pageable pageable, Long total) {
        this(content, pageable);
        this.pageable = pageable;
        this.total = !content.isEmpty() && pageable != null && pageable.getOffset() + pageable.getPageSize() > total ? pageable.getOffset() + content.size() : total;
    }
    public DataTablePageResponse(List<T> content, Pageable pageable) {
        Assert.notNull(content, "Content must not be null!");
//        this.content.addAll(content);
        this.data.addAll(content);
        this.pageable = pageable;
    }

    /**
     * Creates a new {@link PageImpl} with the given content. This will result in the created {@link Page} being identical
     * to the entire {@link List}.
     *
     * @param content must not be {@literal null}.
     */
    public DataTablePageResponse(List<T> content) {
        this(content, null, null == content ? 0l : content.size());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalPages()
     */
    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalElements()
     */
    @Override
    public long getTotalElements() {
        return total;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasNext()
     */
    @Override
    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#isLast()
     */
    @Override
    public boolean isLast() {
        return !hasNext();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#transform(org.springframework.core.convert.converter.Converter)
     */
    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return new DataTablePageResponse<>(getConvertedContent(converter), pageable, total);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DataTablePageResponse<?>)) {
            return false;
        }

        DataTablePageResponse<?> that = (DataTablePageResponse<?>) obj;

        return this.total == that.total && super.equals(obj);
    }

    protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter) {

        Assert.notNull(converter, "Converter must not be null!");

        List<S> result = new ArrayList<>(data.size());

        for (T element : this) {
            result.add(converter.convert(element));
        }

        return result;
    }




    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getNumber()
     */
    public int getNumber() {
        return pageable == null ? 0 : pageable.getPageNumber();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getSize()
     */
    public int getSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getNumberOfElements()
     */
    public int getNumberOfElements() {
        return data.size();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasPrevious()
     */
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#isFirst()
     */
    public boolean isFirst() {
        return !hasPrevious();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#nextPageable()
     */
    public Pageable nextPageable() {
        return hasNext() ? pageable.next() : null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#previousPageable()
     */
    public Pageable previousPageable() {

        if (hasPrevious()) {
            return pageable.previousOrFirst();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasContent()
     */
    public boolean hasContent() {
        return !data.isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getContent()
     */
    public List<T> getContent() {
        //return Collections.unmodifiableList(data);
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#getSort()
     */
    public Sort getSort() {
        return pageable == null ? null : pageable.getSort();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<T> iterator() {
        return data.iterator();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        result += 31 * (int) (total ^ total >>> 32);
        result += 31 * super.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "DataTablePage{" + "pageable=" + pageable + ", data=" + data + ", first=" + first + ", last=" + last + ", number=" + number + ", numberOfElements=" + numberOfElements + ", size=" + size + ", recordsFiltered=" + recordsFiltered + ", recordsTotal=" + recordsTotal + ", total=" + total + ", sort=" + sort + '}';
    }
}
