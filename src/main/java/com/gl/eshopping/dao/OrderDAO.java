package com.gl.eshopping.dao;

import com.gl.eshopping.model.Order;
import com.gl.eshopping.vo.OrderVO;

public interface OrderDAO extends CrudDAO<Order, Long> {
    Order createOrder(OrderVO orderVO);
}
