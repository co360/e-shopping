package com.gl.eshopping.dao.impl;

import com.gl.eshopping.constants.OrderStatus;
import com.gl.eshopping.constants.PaymentMode;
import com.gl.eshopping.exception.DeliveryManNotFoundException;
import com.gl.eshopping.exception.OrderNotFoundException;
import com.gl.eshopping.exception.OrderStatusException;
import com.gl.eshopping.exception.PaymentModeException;
import com.gl.eshopping.model.DeliveryMan;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.repository.DeliveryManRepository;
import com.gl.eshopping.repository.OrderRepository;
import com.gl.eshopping.dao.DeliveryManDAO;
import com.gl.eshopping.vo.GeneralDetailVO;
import com.gl.eshopping.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeliveryManDAOImpl implements DeliveryManDAO {
    private DeliveryManRepository deliveryManRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DeliveryManDAOImpl(DeliveryManRepository deliveryManRepository, OrderRepository orderRepository) {
        this.deliveryManRepository = deliveryManRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<DeliveryMan> findAll() {
        log.debug("Returning Delivery Man(s) from DAO");
        return deliveryManRepository.findAll();
    }

    @Override
    public DeliveryMan findById(Long deliveryManId) {
        log.debug("Returning Delivery Man by Id from DAO");
        return deliveryManRepository.findById(deliveryManId).orElseThrow(() -> new DeliveryManNotFoundException(deliveryManId));
    }

    @Override
    public DeliveryMan save(DeliveryMan newDeliveryMan) {
        log.debug("Saving Delivery Man from DAO");
        return deliveryManRepository.save(newDeliveryMan);
    }

    @Override
    public DeliveryMan update(GeneralDetailVO generalDetailVO, Long deliveryManId) {
        log.debug("Updating Delivery Man from DAO");
        DeliveryMan deliveryMan = findById(deliveryManId);
        deliveryMan.setName(generalDetailVO.getName());
        deliveryMan.setEmail(generalDetailVO.getEmail());
        deliveryMan.setPhoneNo(generalDetailVO.getPhoneNo());
        return deliveryManRepository.saveAndFlush(deliveryMan);
    }

    @Override
    public void delete(DeliveryMan deliveryMan) {
        log.debug("Deleting Delivery Man from DAO");
        deliveryManRepository.delete(deliveryMan);
    }

    @Override
    public void deleteById(Long deliveryManId) {
        log.debug("Updating Delivery Man from DAO");
        deliveryManRepository.deleteById(deliveryManId);
    }

    @Override
    public List<Order> getDeliveryManOrders(Long deliveryManId) {
        log.debug("Getting all Delivery Man Order(s)");
        DeliveryMan deliveryMan = deliveryManRepository.findById(deliveryManId).orElseThrow(() -> new DeliveryManNotFoundException(deliveryManId));
        return deliveryMan.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getDeliveryManOrderById(Long deliveryManId, Long orderId) {
        log.debug("Getting Orders by Delivery Man Id.");
        DeliveryMan deliveryMan = findById(deliveryManId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if (!order.getDeliveryMan().equals(deliveryMan)) {
            throw new OrderNotFoundException(orderId);
        }

        return order;
    }

    @Override
    public Order modifyOrder(Long deliveryManId, Long orderId, OrderModificationVO modification) {
        Order order = getDeliveryManOrderById(deliveryManId, orderId);

        PaymentMode paymentMode = modification.getPaymentMode();
        OrderStatus orderStatus = modification.getOrderStatus();

        if (paymentMode == null) {
            throw new PaymentModeException("Delivery Man cannot change Payment Mode.");
        }

        if (order.getOrderStatus().getDescription().equals("picked up") && orderStatus.getDescription().equals("delivered")) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        } else {
            throw new OrderStatusException("Delivery man cannot change status from " + order.getOrderStatus() + " to " + modification.getOrderStatus());
        }
        return orderRepository.saveAndFlush(order);
    }
}
