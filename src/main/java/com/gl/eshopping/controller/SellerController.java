package com.gl.eshopping.controller;

import com.gl.eshopping.model.Shop;
import com.gl.eshopping.model.Seller;
import com.gl.eshopping.dao.SellerDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class SellerController {
    private SellerDAO sellerDAO;

    @Autowired
    public SellerController(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    @GetMapping(value = "/sellers")
    public ResponseEntity<List<Seller>> getAllSellers() {
        log.debug("Getting all Sellers.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.findAll());
    }

    @GetMapping(value = "/sellers/{sellerId}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long sellerId) {
        log.debug("Getting Sellers by id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.findById(sellerId));
    }

    @PostMapping(value = "/sellers")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        log.debug("Saving Seller.");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerDAO.save(seller));
    }

    @PostMapping(value = "/sellers/{sellerId}/shops")
    public ResponseEntity<Seller> saveShops(@PathVariable Long sellerId, @RequestBody List<Shop> shops) {
        log.debug("Adding Shops to system - Sellers.");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerDAO.saveShops(sellerId, shops));
    }


   /* @PutMapping(value = "/sellers/{sellerId}")
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller, @PathVariable Long sellerId) {
        log.debug("Updating Shop Owner by Shop id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.update(seller, sellerId));
    }*/

    @DeleteMapping(value = "/sellers")
    public ResponseEntity<Object> deleteSeller(@RequestBody Seller seller) {
        log.debug("Deleting Seller.");
        sellerDAO.delete(seller);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/sellers/{sellerId}")
    public ResponseEntity<?> deleteSellerById(@PathVariable Long sellerId) {
        log.debug("Deleting Shop Owner by Seller id.");
        sellerDAO.deleteById(sellerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/sellers/{sellerId}/shops")
    public ResponseEntity<?> deleteShops(@PathVariable Long sellerId, @RequestBody List<Long> shopIds) {
        log.debug("Removing Shops from system - Sellers");
        Seller seller = sellerDAO.deleteShops(sellerId, shopIds);
        return ResponseEntity.noContent().build();
    }
}
