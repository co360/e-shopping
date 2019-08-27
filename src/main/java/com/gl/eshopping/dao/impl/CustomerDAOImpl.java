package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.CustomerNotFoundException;
import com.gl.eshopping.exception.OrderNotFoundException;
import com.gl.eshopping.model.Customer;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.model.OrderStatus;
import com.gl.eshopping.repository.CustomerRepository;
import com.gl.eshopping.repository.OrderRepository;
import com.gl.eshopping.dao.CustomerDAO;
import com.gl.eshopping.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerDAOImpl implements CustomerDAO {
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CustomerDAOImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Returning Customer(s) from DAO");
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long customerId) {
        log.debug("Returning Customer(s) by id from DAO");
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public Customer save(Customer newCustomer) {
        log.debug("Saving Customer(s) from DAO");
        return customerRepository.save(newCustomer);
    }

    public Customer update(Customer customer, Long customerId) {
        log.debug("Updating Customer(s) from DAO");
        return null;
    }

    @Override
    public void delete(Customer customer) {
        log.debug("Deleting Customer from DAO");
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long customerId) {
        log.debug("Deleting Customer by Id from DAO");
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Order> getCustomerOrders(Long customerId) {
        Customer customer = findById(customerId);
        return customer.getOrders().stream().collect(Collectors.toList());
    }

    @Override
    public Order getCustomerOrderById(Long customerId, Long orderId) {
        Customer customer = findById(customerId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if(!order.getCustomer().equals(customer)) {
            throw new IllegalArgumentException("Order does not belong to Customer.");
        }

        return order;
    }

    @Override
    public Order modifyOrder(Long customerId, Long orderId, OrderModificationVO modification) {
        Order order = getCustomerOrderById(customerId, orderId);

        if(!order.getOrderStatus().getDescription().equals("delivered") && modification.getOrderStatus().getDescription().equals("cancelled")) {
            order.setOrderStatus(OrderStatus.CANCELLED_BY_USER);
        }
        return orderRepository.saveAndFlush(order);
    }
}
