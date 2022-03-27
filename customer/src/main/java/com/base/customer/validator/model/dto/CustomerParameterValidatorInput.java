package com.base.customer.validator.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CustomerParameterValidatorInput implements Serializable {

    private static final long serialVersionUID = -7749084230349961672L;

    private String name;
    private String surname;
}
