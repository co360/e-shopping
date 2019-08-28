package com.gl.eshopping.dao;

import com.gl.eshopping.model.Customer;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.vo.GeneralDetailVO;
import com.gl.eshopping.vo.OrderModificationVO;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer, Long> {
    List<Order> getCustomerOrders(Long customerId);

    Order modifyOrder(Long customerId, Long orderId, OrderModificationVO modification);

    Order getCustomerOrderById(Long customerId, Long orderId);

    Customer update(GeneralDetailVO generalDetailVO, Long customerId);
}
