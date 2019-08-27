package com.gl.eshopping.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "seller")
public class Seller extends GeneralDetails {
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "seller-shops")
    private Set<Shop> shops = new HashSet<>();
}
