package com.base.bookservice.model.dto;

import com.base.model.dto.GenericDto;
import lombok.Data;

@Data
public class BookDto extends GenericDto {

    private static final long serialVersionUID = 1820176473200516172L;

    private String id;
    private String name;
    private String seller;
    private Integer remainingStock;
}
