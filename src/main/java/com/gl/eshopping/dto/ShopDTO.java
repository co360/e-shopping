package com.gl.eshopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ShopDTO extends GeneralDetailsDTO {
    private SellerDTO restaurantOwner;

    private Set<ProductDTO> foodItems = new HashSet<>();

    private Set<OrderDTO> orders = new HashSet<>();
}
