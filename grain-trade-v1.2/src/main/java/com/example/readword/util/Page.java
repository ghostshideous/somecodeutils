package com.example.readword.util;


import lombok.Data;

@Data
public class Page {
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;

    public Page(int pageNum, int pageSize, long total) {
        this.pageNum = (pageNum <= 0) ? 1 : pageNum;
        this.pageSize = (pageSize<=0)?10:pageSize;
        this.total = total;
    }

    public static Page page(int pageNum, int pageSize, long total) {
        return new Page(pageNum, pageSize, total);
    }

    private int getPages() {
        return (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
    }

    public int offset() {
        return getPages() == 0 ? 0 : (pageNum > getPages() ? (getPages() - 1) * pageSize : (pageNum - 1) * pageSize);
    }
}
