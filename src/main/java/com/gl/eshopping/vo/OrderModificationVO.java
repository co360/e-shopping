package com.gl.eshopping.vo;

import com.gl.eshopping.model.OrderStatus;
import com.gl.eshopping.model.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModificationVO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
