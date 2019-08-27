package com.gl.eshopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DeliveryManDTO extends GeneralDetailsDTO {
    private Set<OrderDTO> orders = new HashSet<>();
}
