package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.CustomerNotFoundException;
import com.gl.eshopping.exception.ProductFoundException;
import com.gl.eshopping.exception.OrderNotFoundException;
import com.gl.eshopping.exception.ShopNotFoundException;
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
        order.setTotalPrice(orderVO.getTotalPrice());

        orderRepository.save(order);

        List<OrderProduct> orderProducts = orderVO.getOrderProductVOS().stream()
                .map(orderProductVO -> orderFoodVoConverter(orderProductVO)).collect(Collectors.toList());
        orderProducts.forEach(orderProduct -> orderProduct.setOrder(order));

        orderProductRepository.saveAll(orderProducts);
        return order;
    }

    private OrderProduct orderFoodVoConverter(OrderProductVO orderProductVo) {
        log.debug("Converting orderProductVo into orderProduct");
        OrderProduct orderProduct = new OrderProduct();
        Long productId = orderProductVo.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductFoundException(productId));
        orderProduct.setProduct(product);
        orderProduct.setQuantity(orderProductVo.getQuantity());
        orderProduct.setTotalPrice(orderProductVo.getTotalPrice());
        return orderProduct;
    }

    private DeliveryMan getDeliveryMan() {
        // TODO implement this in some non random way.
        log.debug("Getting a delivery man.");
        List<DeliveryMan> deliveryMEN = deliveryManRepository.findAll();
        Random random = new Random();
        return deliveryMEN.get(random.nextInt(deliveryMEN.size()));
    }
}
