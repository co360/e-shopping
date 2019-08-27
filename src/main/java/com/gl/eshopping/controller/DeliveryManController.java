package com.gl.eshopping.controller;

import com.gl.eshopping.model.DeliveryMan;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.dao.DeliveryManDAO;
import com.gl.eshopping.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeliveryManController {
    private DeliveryManDAO deliveryManDAO;

    @Autowired
    public DeliveryManController(DeliveryManDAO deliveryManDAO) {
        this.deliveryManDAO = deliveryManDAO;
    }

    @GetMapping(value = "/deliverymans")
    public ResponseEntity<List<DeliveryMan>> getAllDeliveryMans() {
        log.debug("Getting all Delivery Mans.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.findAll());
    }

    @GetMapping(value = "/deliverymans/{deliveryManId}")
    public ResponseEntity<DeliveryMan> getDeliveryMan(@PathVariable Long deliveryManId) {
        log.debug("Getting Delivery Man by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.findById(deliveryManId));
    }

    @GetMapping(value = "/deliverymans/{deliveryManId}/orders")
    public ResponseEntity<List<Order>> getDeliveryManOrders(@PathVariable Long deliveryManId) {
        log.debug("Getting Orders assigned to Delivery Man.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.getDeliveryManOrders(deliveryManId));
    }

    @GetMapping(value = "/deliverymans/{deliveryManId}/orders/{orderId}")
    public ResponseEntity<Order> getDeliveryManOrderById(@PathVariable Long deliveryManId, @PathVariable Long orderId) {
        log.debug("Getting Order assigned to Delivery Man using given OrderId.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.getDeliveryManOrderById(deliveryManId, orderId));
    }

    @PostMapping(value = "/deliverymans")
    public ResponseEntity<DeliveryMan> saveDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        log.debug("Saving Delivery Man.");
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryManDAO.save(deliveryMan));
    }

   /* @PutMapping(value = "/deliverymans/{deliveryManId}")
    public ResponseEntity<DeliveryMan> updateDeliveryMan(@RequestBody DeliveryMan deliveryMan, @PathVariable Long deliveryManId) {
        log.debug("Updating Delivery Man by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.update(deliveryMan, deliveryManId));
    }*/

    @PutMapping(value = "/deliverymans/{deliveryManId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long deliveryManId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Delivery Man Order.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.modifyOrder(deliveryManId, orderId, modification));
    }

    @DeleteMapping(value = "/deliverymans")
    public ResponseEntity<?> deleteDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        log.debug("Deleting Delivery Man.");
        deliveryManDAO.delete(deliveryMan);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/deliverymans/{deliveryManId}")
    public ResponseEntity<?> deleteDeliveryManById(@PathVariable Long deliveryManId) {
        log.debug("Deleting Delivery Man by id.");
        deliveryManDAO.deleteById(deliveryManId);
        return ResponseEntity.noContent().build();
    }
}
