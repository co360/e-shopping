package com.gl.eshopping.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity{

    /*
     * Private fields
     */
    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_active")
    private boolean active = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "category-products")
    private Set<Product> products = new HashSet<>();



}
