package com.gl.eshopping.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GeneralDetailsDTO extends BaseEntityDTO {
    private String name;
    private String phoneNo;
    private String email;
}
