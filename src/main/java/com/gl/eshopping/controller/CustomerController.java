package com.gl.eshopping.controller;

import  com.gl.eshopping.model.Customer;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.dao.CustomerDAO;
import com.gl.eshopping.vo.GeneralDetailVO;
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



    /**
     * Getting All Customer from the database
     * @return Customer list
     */
    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.debug("Getting All Customers.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.findAll());
    }


    /**
     * Getting Customer By Id
     * @param take Customer Id as parameter
     * @return Customer
     */
    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        log.debug("Getting Customers By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.findById(customerId));
    }


    /**
     * Getting Customer Orders.
     * @param take Customer Id as parameter
     * @return list of order of specified Customer id
     */
    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long customerId) {
        log.debug("Getting Customer Orders.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.getCustomerOrders(customerId));
    }


    /**
     * Getting Order by order id of particular Customer id
     * @param method take Customer Id and
     * @param Order Id as parameter
     * @return Order
     */
    @GetMapping(value = "/customer/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> getCustomerOrderById(@PathVariable Long customerId, @PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.getCustomerOrderById(customerId, orderId));
    }


    /**
     *
     * @param This method take new customer as parameter to store it
     * @return return that new stored customer
     */
    @PostMapping(value = "/user/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        log.debug("Saving Customer.");
        customerDAO.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDAO.save(customer));
    }


    /**
     *
     * @param This method take updated customer as parameter to store it
     * @return update customer
     */
    @PutMapping(value = "/user/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long customerId) {
        log.debug("Updating Customer.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.update(generalDetailVO, customerId));
    }


    /**
     *
     * @param This method take customerId
     * @param orderId and
     * @param modification value object to modify any changes of  the order placed by customer
     * @return modified order
     */
    @PutMapping(value = "/user/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Customer Order.");
        return ResponseEntity.status(HttpStatus.OK).body(customerDAO.modifyOrder(customerId, orderId, modification));
    }


    /**
     *
     * @param This method take customer as parameter for delete that customer
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/customers")
    public ResponseEntity<?> deleteCustomer(@RequestBody Customer customer) {
        log.debug("Deleting Customer.");
        customerDAO.delete(customer);
        return ResponseEntity.noContent().build();
    }


    /**
     *
     * @param This method take customer id as parameter for delete that customer by customer id
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long customerId) {
        log.debug("Deleting Customer By Id.");
        customerDAO.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
