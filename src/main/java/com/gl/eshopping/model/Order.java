package com.gl.eshopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gl.eshopping.constants.OrderStatus;
import com.gl.eshopping.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Order")
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "user-orders")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_man_id")
    @JsonBackReference(value = "delivery-man-orders")
    private DeliveryMan deliveryMan;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference(value = "shop-orders")
    private Shop shop;

    @Column
    private LocalDateTime timestamp;

    @Column
    private PaymentMode paymentMode;

    @Column
    private OrderStatus orderStatus;

    @Column
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "order-orderProducts")
    private Set<OrderProduct> orderProducts = new HashSet<>();
}
