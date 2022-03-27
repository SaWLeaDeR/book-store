package com.base.error.model;

import com.base.model.dto.GenericDto;

import java.util.Date;

public abstract class GenericErrorDto extends GenericDto implements ErrorSign {

    private static final long serialVersionUID = -7514271941201316525L;

    private Date createDate = new Date();

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
