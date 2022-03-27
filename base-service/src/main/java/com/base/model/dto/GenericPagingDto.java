package com.base.model.dto;

import java.util.List;

public class GenericPagingDto<T extends GenericDto> extends GenericDto {

    private static final long serialVersionUID = -2635738713731751281L;

    private int page;

    private int limit;

    private long totalCount;

    private List<T> types;

    public GenericPagingDto(int page, int limit, long totalCount, List<T> types) {
        this.setPage(page);
        this.setLimit(limit);
        this.setTotalCount(totalCount);
        this.setTypes(types);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getTypes() {
        return types;
    }

    public void setTypes(List<T> types) {
        this.types = types;
    }
}
