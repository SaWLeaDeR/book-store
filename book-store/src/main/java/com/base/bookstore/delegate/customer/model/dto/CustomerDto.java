package com.base.bookstore.delegate.customer.model.dto;

import com.base.model.dto.GenericDto;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerDto extends GenericDto {

    private static final long serialVersionUID = 6248109983196499969L;

    private String name;
    private String surname;
}
