package com.gl.eshopping.vo;

import com.gl.eshopping.model.PaymentMode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderVO {
    private Long customerId;
    private Long shopId;
    private List<OrderProductVO> orderProductVOS;

    private PaymentMode paymentMode;

    private LocalDateTime timestamp;
    private Double totalPrice;
}
