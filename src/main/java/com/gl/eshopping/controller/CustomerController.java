package com.gl.eshopping.controller;

import com.gl.eshopping.model.Customer;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.dao.CustomerDAO;
import com.gl.eshopping.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CustomerController {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.debug("Getting All Customers.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.findAll());
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        log.debug("Getting Customers By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.findById(customerId));
    }

    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long customerId) {
        log.debug("Getting Customer Orders.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.getCustomerOrders(customerId));
    }

    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> getCustomerOrderById(@PathVariable Long customerId, @PathVariable Long orderId){
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.getCustomerOrderById(customerId, orderId));
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        log.debug("Saving Customer.");
        customerDAO.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDAO.save(customer));
    }

   /* @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        log.debug("Updating Customer.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.update(customer, customerId));
    }*/

    @PutMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.modifyOrder(customerId, orderId, modification));
    }

    @DeleteMapping(value = "/customers")
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer) {
        log.debug("Deleting Customer.");
        customerDAO.delete(customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId) {
        log.debug("Deleting Customer By Id.");
        customerDAO.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
