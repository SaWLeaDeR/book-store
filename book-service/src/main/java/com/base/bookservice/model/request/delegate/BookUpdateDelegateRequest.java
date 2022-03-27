package com.base.bookservice.model.request.delegate;

import com.base.model.request.GenericRequest;
import lombok.Data;

@Data
public class BookUpdateDelegateRequest extends GenericRequest {

    private String id;
    private String name;
    private String seller;
    private Integer stock;
}
