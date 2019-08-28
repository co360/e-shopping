package com.gl.eshopping.vo;

import com.gl.eshopping.constants.OrderStatus;
import com.gl.eshopping.constants.PaymentMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModificationVO {
    OrderStatus orderStatus;
    PaymentMode paymentMode;
}
