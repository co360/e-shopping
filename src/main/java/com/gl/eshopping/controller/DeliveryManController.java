package com.gl.eshopping.controller;

import com.gl.eshopping.model.DeliveryMan;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.dao.DeliveryManDAO;
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
public class DeliveryManController {
    private DeliveryManDAO deliveryManDAO;

    @Autowired
    public DeliveryManController(DeliveryManDAO deliveryManDAO) {
        this.deliveryManDAO = deliveryManDAO;
    }


    /**
     * Getting All DeliveryMan from the database
     * @return  list of DeliveryMan
     */
    @GetMapping(value = "/deliverymans")
    public ResponseEntity<List<DeliveryMan>> getAllDeliveryMans() {
        log.debug("Getting all Delivery Mans.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.findAll());
    }


    /**
     * Getting DeliveryMan By Id
     * @param take DeliveryMan Id as parameter
     * @return DeliveryMan
     */
    @GetMapping(value = "/deliverymans/{deliveryManId}")
    public ResponseEntity<DeliveryMan> getDeliveryMan(@PathVariable Long deliveryManId) {
        log.debug("Getting Delivery Man by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.findById(deliveryManId));
    }


    /**
     * Getting Orders deliver by that man.
     * @param take deliveryman id as parameter
     * @return list of order of specified deliveryman id
     */
    @GetMapping(value = "/deliverymans/{deliveryManId}/orders")
    public ResponseEntity<List<Order>> getDeliveryManOrders(@PathVariable Long deliveryManId) {
        log.debug("Getting Orders assigned to Delivery Man.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.getDeliveryManOrders(deliveryManId));
    }


    /**
     * Getting Order by order id of particular deliveryManId
     * @param This  method take deliveryManId  and
     * @param Order Id as parameter
     * @return Order
     */
    @GetMapping(value = "/deliverymans/{deliveryManId}/orders/{orderId}")
    public ResponseEntity<Order> getDeliveryManOrderById(@PathVariable Long deliveryManId, @PathVariable Long orderId) {
        log.debug("Getting Order assigned to Delivery Man using given OrderId.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.getDeliveryManOrderById(deliveryManId, orderId));
    }


    /**
     *
     * @param This method take new deliveryman as parameter to store iifo in database
     * @return return that new stored deliveryman
     */
    @PostMapping(value = "/admin/deliverymans")
    public ResponseEntity<DeliveryMan> saveDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        log.debug("Saving Delivery Man.");
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryManDAO.save(deliveryMan));
    }


    /**
     *
     * @param This method take updated deliveryman info  as parameter to store it database
     * @return updated deliveryman info
     */
    @PutMapping(value = "/admin/deliverymans/{deliveryManId}")
    public ResponseEntity<DeliveryMan> updateDeliveryMan(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long deliveryManId) {
        log.debug("Updating Delivery Man by id.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.update(generalDetailVO, deliveryManId));
    }


    /**
     *
     * @param This method take deliveryman id
     * @param order id and
     * @param modification value object to modify any changes of  the delivered  by that deliveryman
     * @return modified order
     */
    @PutMapping(value = "/admin/deliverymans/{deliveryManId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long deliveryManId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Delivery Man Order.");
        return ResponseEntity.status(HttpStatus.OK).body(deliveryManDAO.modifyOrder(deliveryManId, orderId, modification));
    }


    /**
     *
     * @param This method take deliveryman as parameter for delete that deliveryman i.e de_active that deliveryman
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/deliverymans")
    public ResponseEntity<?> deleteDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        log.debug("Deleting Delivery Man.");
        deliveryManDAO.delete(deliveryMan);
        return ResponseEntity.noContent().build();
    }


    /**
     *
     * @param This method take deliveryman id as parameter for delete that deliveryman by deliveryman id
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/deliverymans/{deliveryManId}")
    public ResponseEntity<?> deleteDeliveryManById(@PathVariable Long deliveryManId) {
        log.debug("Deleting Delivery Man by id.");
        deliveryManDAO.deleteById(deliveryManId);
        return ResponseEntity.noContent().build();
    }
}
