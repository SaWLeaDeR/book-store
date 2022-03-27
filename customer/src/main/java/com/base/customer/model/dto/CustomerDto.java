package com.base.customer.model.dto;

import com.base.model.dto.GenericDto;
import lombok.Data;

@Data
public class CustomerDto extends GenericDto {

    private static final long serialVersionUID = 3567559993515244102L;

    private String id;
    private String name;
    private String surname;
}
