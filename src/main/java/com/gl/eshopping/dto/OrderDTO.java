package com.gl.eshopping.dto;

import com.gl.eshopping.constants.OrderStatus;
import com.gl.eshopping.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDTO extends BaseEntityDTO {
    private CustomerDTO customer;

    private DeliveryManDTO deliveryGuy;

    private ShopDTO restaurant;

    private LocalDateTime timestamp;

    private PaymentMode paymentMode;

    private OrderStatus orderStatus;

    private Double totalPrice;

    private Set<OrderProductDTO> orderFoodItems = new HashSet<>();
}
