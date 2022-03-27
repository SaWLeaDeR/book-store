package com.base.model.request;

public abstract class GenericPagingRequest extends GenericRequest{

    private static final long serialVersionUID = -1237075097345454263L;

    private Integer page;

    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
