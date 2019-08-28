package com.gl.eshopping.controller;

import com.gl.eshopping.model.Order;
import com.gl.eshopping.dao.OrderDAO;
import com.gl.eshopping.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {
    private OrderDAO orderDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    /**
     *
     * @param This method take order value object as parameter at the time of ordering some product
     * @return new order
     */
    @PostMapping("/user/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderVO orderVO) {
        log.debug("Creating new Order.");
        return ResponseEntity.status(HttpStatus.OK).body(orderDAO.createOrder(orderVO));
    }


}
