package com.base.bookservice.validation.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class BookParameterValidatorInput implements Serializable {

    private static final long serialVersionUID = 4006924682913153778L;

    private String id;
    private String name;
    private String seller;
    private Integer stock;


}
