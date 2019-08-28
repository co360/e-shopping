package com.gl.eshopping.dao.impl;

import com.gl.eshopping.constants.OrderStatus;
import com.gl.eshopping.exception.*;
import com.gl.eshopping.model.*;
import com.gl.eshopping.repository.*;
import com.gl.eshopping.dao.OrderDAO;
import com.gl.eshopping.vo.OrderProductVO;
import com.gl.eshopping.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderDAOImpl implements OrderDAO {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ShopRepository shopRepository;
    private ProductRepository productRepository;
    private OrderProductRepository orderProductRepository;
    private DeliveryManRepository deliveryManRepository;

    @Autowired
    public OrderDAOImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ShopRepository shopRepository, ProductRepository productRepository,
                        OrderProductRepository orderProductRepository, DeliveryManRepository deliveryManRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.deliveryManRepository = deliveryManRepository;
    }

    @Override
    public List<Order> findAll() {
        log.debug("Getting Orders from DAO.");
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        log.debug("Getting Order by Id from DAO.");
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public Order save(Order newOrder) {
        log.debug("Saving order from DAO.");
        return orderRepository.save(newOrder);
    }

    public Order update(Order order, Long orderId) {
        log.debug("Updating Order from DAO.");
        return null;
    }

    @Override
    public void delete(Order order) {
        log.debug("Deleting order from DAO.");
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long orderId) {
        log.debug("Deleting order by id from DAO.");
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order createOrder(OrderVO orderVO) {
        log.debug("Create new order.");
        Order order = toOrder(orderVO);
        orderRepository.save(order);

        List<OrderProduct> orderProducts = orderVO.getOrderProductVOS().stream()
                .map(orderProductVO -> toOrderFood(orderProductVO)).collect(Collectors.toList());

        Double totalPrice = 0.0;

        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getTotalPrice();
        }

        if (totalPrice != orderVO.getTotalPrice()) {
            throw new PriceMismatchException("Total Price for this order should be " + totalPrice + " but found " + orderVO.getTotalPrice());
        }

        order.setTotalPrice(totalPrice);

        orderProducts.forEach(orderFoodItem -> orderFoodItem.setOrder(order));


        orderProductRepository.saveAll(orderProducts);
        return order;
    }

    private Order toOrder(OrderVO orderVO) {
        Long customerId = orderVO.getCustomerId();
        Long shopId = orderVO.getShopId();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ShopNotFoundException(shopId));

        Order order = new Order();
        order.setCustomer(customer);
        order.setShop(shop);
        order.setDeliveryMan(getDeliveryMan());
        order.setOrderStatus(OrderStatus.APPROVED);
        order.setPaymentMode(orderVO.getPaymentMode());
        order.setTimestamp(LocalDateTime.now());

        return order;
    }

    private OrderProduct toOrderFood(OrderProductVO orderProductVo) {
        log.debug("Converting orderProductVo into orderProduct");
       OrderProduct orderProduct = new OrderProduct();
        Long productId = orderProductVo.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        orderProduct.setProduct(product);
        orderProduct.setQuantity(orderProductVo.getQuantity());
        Double totalPrice = orderProductVo.getQuantity() * product.getPrice();
        if (totalPrice != orderProductVo.getTotalPrice()) {
            throw new PriceMismatchException("Total Price for " + product.getName() + " should be " + totalPrice + " but found " + orderProductVo.getTotalPrice());
        }
        orderProduct.setTotalPrice(totalPrice);
        return orderProduct;
    }

    private DeliveryMan getDeliveryMan() {
        log.debug("Getting a delivery man.");
        List<DeliveryMan> deliveryMans = deliveryManRepository.findAll();
        Random random = new Random();
        return deliveryMans.get(random.nextInt(deliveryMans.size()));
    }
}
