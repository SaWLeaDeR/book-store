package com.base.bookservice.model.request;

import com.base.model.request.GenericRequest;
import lombok.Data;

@Data
public class BookGetRequest extends GenericRequest {

    private static final long serialVersionUID = 4786694293154578076L;

    private String name;
    private String seller;
}
