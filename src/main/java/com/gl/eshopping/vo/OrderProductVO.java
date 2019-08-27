package com.gl.eshopping.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductVO {
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
}
