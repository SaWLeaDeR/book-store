package com.base.bookservice.model.request;

import com.base.model.request.GenericRequest;
import lombok.Data;

@Data
public class BookUpdateRequest extends GenericRequest {

    private static final long serialVersionUID = 6522210937291690685L;

    private String name;
    private String seller;
    private Integer stock;
}
