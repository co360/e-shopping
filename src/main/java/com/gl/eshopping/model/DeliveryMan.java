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
@Table(name = "delivery_man")
public class DeliveryMan extends GeneralDetails {
    @OneToMany(mappedBy = "deliveryMan", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "delivery-man-orders")
    private Set<Order> orders = new HashSet<>();
}
