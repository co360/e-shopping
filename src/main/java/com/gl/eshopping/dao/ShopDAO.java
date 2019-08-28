package com.gl.eshopping.dao;


import com.gl.eshopping.model.Order;
import com.gl.eshopping.model.Product;
import com.gl.eshopping.model.Shop;
import com.gl.eshopping.vo.OrderModificationVO;

import java.util.List;

public interface ShopDAO extends CrudDAO<Shop, Long> {
    Shop addProducts(Long shopId, List<Product> products);

    Shop removeProducts(Long shopId, List<Long> productIds);

    List<Order> getShopOrders(Long shopId);

    Order getShopOrderById(Long shopId, Long orderId);

    Order modifyOrder(Long shopId, Long orderId, OrderModificationVO modification);
}
