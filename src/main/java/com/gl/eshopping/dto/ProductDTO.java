package com.gl.eshopping.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductDTO extends BaseEntityDTO {
    private String name;
    private Double price;
    private ShopDTO shop;
    private String brand;
    private String description;

    public ProductDTO(String name, Double price, ShopDTO shop, String brand, String description) {
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.brand = brand;
        this.description = description;
    }



}
