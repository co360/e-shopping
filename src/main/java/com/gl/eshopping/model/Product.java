package com.gl.eshopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Product")
@Table(name = "product")
public class Product extends BaseEntity {
    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private String description;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference(value = "shop-products")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-products")
    private Category category;

    public Product() {
    }
}
