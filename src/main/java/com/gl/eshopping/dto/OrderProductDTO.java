package com.gl.eshopping.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO extends BaseEntityDTO {
    private ProductDTO foodItem;
    private OrderDTO order;
    private Integer quantity;
    private Double totalPrice;
}
