package com.market.vo;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;



import java.util.List;

/**
 * Created by Elias on 2019/4/17
 */
public class PageResult <T>{
    private List<T> data;
    private long total;
    private int currentPage;
    private int pages;
    private int size;
    private boolean hasNext;
    private boolean hasPre;
    private int[] pageNums;
    public PageResult(){}

    public PageResult(List<T> list){
        if (list instanceof Page){
            Page<T> page = (Page<T>) list;
            PageInfo info =new PageInfo(page);
            this.total = info.getTotal();
            this.data=info.getList();
            this.currentPage =  info.getPageNum();
            this.size = info.getPageSize();
            this.pages = info.getPages();
            this.hasNext = info.isHasNextPage();
            this.hasPre = info.isHasPreviousPage();
            this.pageNums=info.getNavigatepageNums();
        }
    }

    public int[] getPageNums() {
        return pageNums;
    }

    public void setPageNums(int[] pageNums) {
        this.pageNums = pageNums;
    }

    public PageResult(PageInfo<T> info){
        this.data = info.getList();
        this.total = info.getTotal();
        this.currentPage = info.getPageNum();
        this.size = info.getPageSize();
        this.pages = info.getPages();
        this.hasNext = info.isHasNextPage();
        this.hasPre = info.isHasPreviousPage();
        this.pageNums=info.getNavigatepageNums();
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "data=" + data +
                ", currentPage=" + currentPage +
                ", size=" + size +
                '}';
    }

    public List<T> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPages() {
        return pages;
    }

    public int getSize() {
        return size;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPre() {
        return hasPre;
    }
}
