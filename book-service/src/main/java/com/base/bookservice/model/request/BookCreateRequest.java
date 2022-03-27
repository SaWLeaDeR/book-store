package com.base.bookservice.model.request;

import com.base.model.request.GenericRequest;
import lombok.Data;

@Data
public class BookCreateRequest extends GenericRequest {

    private static final long serialVersionUID = 6227831594559295708L;

    private String name;
    private String seller;
    private Integer stockCount;
}
