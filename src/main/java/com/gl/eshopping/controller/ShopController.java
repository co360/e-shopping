package com.gl.eshopping.controller;


import com.gl.eshopping.model.Order;

import com.gl.eshopping.dao.ShopDAO;
import com.gl.eshopping.model.Product;
import com.gl.eshopping.model.Shop;
import com.gl.eshopping.vo.OrderModificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ShopController {
    private ShopDAO shopDAO;

    @Autowired
    public ShopController(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }



    /**
     * Getting Shop By Id
     * @param take Shop Id as parameter
     * @return Shop
     */
    @GetMapping(value = "/shops/{shopId}")
    public ResponseEntity<Shop> getShop(@PathVariable Long shopId) {
        log.debug("Getting Shop by id.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.findById(shopId));
    }


    /**
     * Getting Shop Orders.
     * @param take Shop Id as parameter
     * @return list of order of specified Shop id
     */
    @GetMapping(value = "/shops/{shopId}/orders")
    public ResponseEntity<List<Order>> getShopOrders(@PathVariable Long shopId) {
        log.debug("Getting all Orders using Shop Id.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.getShopOrders(shopId));
    }


    /**
     * Getting Order by order id of particular Shop id
     * @param This method take Shop Id and
     * @param Order Id as parameter
     * @return Order
     */
    @GetMapping(value = "/shops/{shopId}/orders/{orderId}")
    public ResponseEntity<Order> getShopOrderById(@PathVariable Long shopId, @PathVariable Long orderId) {
        log.debug("Getting Shop Order By Shop Id.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.getShopOrderById(shopId, orderId));
    }


    /**
     *
     * @param This method take new Product as parameter to store it in shop
     * @param by shop id
     * @return return that new stored Product
     */
    @PostMapping(value = "/admin/shops/{shopId}/products")
    public ResponseEntity<Shop> addProducts(@PathVariable Long shopId, @RequestBody List<Product> products) {
        log.debug("Adding Products to Shop.");
        return ResponseEntity.status(HttpStatus.CREATED).body(shopDAO.addProducts(shopId, products));
    }


    /**
     *
     * @param This method take shopId
     * @param orderId and
     * @param modification value object to modify any changes of the order placed by customer
     * @return modified order
     */
    @PutMapping(value = "/admin/shops/{shopId}/orders/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable Long shopId, @PathVariable Long orderId, @RequestBody OrderModificationVO modification) {
        log.debug("Modifying Shop Order.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.modifyOrder(shopId, orderId, modification));
    }


    /**
     *
     * @param This method take shop id and
     * @param product as parameter for delete that product of shop
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/shops/{shopId}/products")
    public ResponseEntity<?> removeProducts(@PathVariable Long shopId, @RequestBody List<Long> products) {
        log.debug("Removing products from shops");
        shopDAO.removeProducts(shopId, products);
        return ResponseEntity.noContent().build();
    }
}
