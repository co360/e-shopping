package com.gl.eshopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shop")
public class Shop extends GeneralDetails {

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonBackReference(value = "seller-shops")
    private Seller seller;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "shop-products")
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "shop-orders")
    private Set<Order> orders = new HashSet<>();
}
