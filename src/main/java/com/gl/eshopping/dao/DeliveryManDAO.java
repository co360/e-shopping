package com.gl.eshopping.dao;

import com.gl.eshopping.model.DeliveryMan;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.vo.OrderModificationVO;

import java.util.List;

public interface DeliveryManDAO extends CrudDAO<DeliveryMan, Long> {
    List<Order> getDeliveryManOrders(Long deliveryManId);

    Order getDeliveryManOrderById(Long deliveryManId, Long orderId);

    Order modifyOrder(Long deliveryManId, Long orderId, OrderModificationVO modification);
}
