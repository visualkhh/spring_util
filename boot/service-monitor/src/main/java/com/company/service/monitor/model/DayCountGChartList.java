package com.company.service.monitor.model;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


public class DayCountGChartList implements List<DayCount> {
    List<DayCount> datas;

    public DayCountGChartList(List<DayCount> datas) {
        this.datas = datas;
    }

    @Override
    public int size() {
        return this.datas.size();
    }

    @Override
    public boolean isEmpty() {
        return this.datas.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.datas.contains(o);
    }

    @Override
    public Iterator<DayCount> iterator() {
        return this.datas.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.datas.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.datas.toArray(a);
    }

    @Override
    public boolean add(DayCount dayCount) {
        return this.datas.add(dayCount);
    }

    @Override
    public boolean remove(Object o) {
        return this.datas.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.datas.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends DayCount> c) {
        return this.datas.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends DayCount> c) {
        return this.datas.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.datas.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.datas.retainAll(c);
    }

    @Override
    public void clear() {
        this.datas.clear();
    }

    @Override
    public DayCount get(int index) {
        return this.datas.get(index);
    }

    @Override
    public DayCount set(int index, DayCount element) {
        return this.datas.set(index, element);
    }

    @Override
    public void add(int index, DayCount element) {
        this.datas.add(index, element);
    }

    @Override
    public DayCount remove(int index) {
        return this.datas.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.datas.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.datas.lastIndexOf(o);
    }

    @Override
    public ListIterator<DayCount> listIterator() {
        return this.datas.listIterator();
    }

    @Override
    public ListIterator<DayCount> listIterator(int index) {
        return this.datas.listIterator(index);
    }

    @Override
    public List<DayCount> subList(int fromIndex, int toIndex) {
        return this.datas.subList(fromIndex, toIndex);
    }

    public String getBarChartURL() {
        if(!CollectionUtils.isEmpty(datas)) {
            DayCount maxDayCount = this.datas.stream().max(Comparator.comparing(DayCount::getCount)).get();

            String collect = this.datas.stream()
//                    .map((it) -> it.getCount().doubleValue() / maxDayCount.getCount().doubleValue() * 100d)
//                    .map((it) -> it.getCount().doubleValue() / maxDayCount.getCount().doubleValue() * 100d)
//                    .map(it -> Integer.toString(it.intValue()))
                    .map(it -> Integer.toString(it.getCount()))
                    .collect(Collectors.joining(","));
            return "https://chart.googleapis.com/chart?cht=bvg&chs=350x80&chd=t:"+collect+"&chbh=a&chco=3eb6e6,c6d9fd&chds=0,"+(maxDayCount.getCount()+10)+ "&chxt=y&chxr=0,0,"+(maxDayCount.getCount()+10)+"&chm=N,000000,0,,10|N,000000,1,,10|N,000000,2,,10";
//            return "https://chart.googleapis.com/chart?cht=bvg&chs=300x80&chd=t:"+collect+"&chbh=a&chco=3eb6e6,c6d9fd&chds=0,"+maxDayCount.getCount()+ "&chm=N,000000,0,,10|N,000000,1,,10|N,000000,2,,10";
//            return "https://chart.googleapis.com/chart?cht=bvg&chs=200x80&chd=t:"+collect+"&chbh=a&chco=3eb6e6,c6d9fd&chds=0,"+maxDayCount.getCount()+ "&chxt=y";
        } else {
            return "";
        }
//        return "https://chart.googleapis.com/chart?cht=bvg&chs=150x100&chd=t:"+collect+"&chxt=y&chbh=a&chxs=0&chm=N,000000,0,,10";
    }
}
