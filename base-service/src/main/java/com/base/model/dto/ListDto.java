package com.base.model.dto;

import java.util.Collections;
import java.util.List;

public class ListDto<T extends GenericDto> extends GenericDto {

    private static final long serialVersionUID = -4357237976755111638L;

    private List<T> listData;
    private long totalCount;

    public ListDto(long totalCount, List<T> listData) {
        this.setTotalCount(totalCount);
        this.setListData(listData);
    }

    public ListDto(long totalCount) {
        this.setTotalCount(totalCount);
        this.setListData(Collections.emptyList());
    }

    public ListDto() {
        this.setTotalCount(0L);
        this.setListData(Collections.emptyList());
    }

    public ListDto(List<T> listData) {
        this.setTotalCount(listData.size());
        this.setListData(listData);
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
