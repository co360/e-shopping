package com.gl.eshopping.controller;

import com.gl.eshopping.model.Product;
import com.gl.eshopping.model.Order;
import com.gl.eshopping.model.Shop;
import com.gl.eshopping.dao.ShopDAO;
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

    @GetMapping(value = "/shops")
    public ResponseEntity<List<Shop>> getAllRestaurants() {
        log.debug("Getting all Shops.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.findAll());
    }

    @GetMapping(value = "/shops/{shopId}")
    public ResponseEntity<Shop> getShop(@PathVariable Long shopId) {
        log.debug("Getting Shop by id.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.findById(shopId));
    }

    @GetMapping(value = "/shops/{shopId}/orders")
    public ResponseEntity<List<Order>> getShopOrders(@PathVariable Long shopId) {
        log.debug("Getting all Orders using shopId.");
        return ResponseEntity.status(HttpStatus.OK).body(shopDAO.getShopOrders(shopId));
    }

    @PostMapping(value = "/shops/{shopId}/products")
    public ResponseEntity<Shop> addProducts(@PathVariable Long shopId, @RequestBody List<Product> products) {
        log.debug("Adding products to shops.");
        return ResponseEntity.status(HttpStatus.CREATED).body(shopDAO.addProducts(shopId, products));
    }

    @DeleteMapping(value = "/shops/{shopId}/products")
    public ResponseEntity<?> removeProducts(@PathVariable Long shopId, @RequestBody List<Long> productIds) {
        log.debug("Removing products from shops");
        shopDAO.removeProducts(shopId, productIds);
        return ResponseEntity.noContent().build();
    }
}
