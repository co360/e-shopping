package com.gl.eshopping.controller;

import com.gl.eshopping.model.Shop;
import com.gl.eshopping.model.Seller;
import com.gl.eshopping.dao.SellerDAO;
import com.gl.eshopping.vo.GeneralDetailVO;
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


    /**
     * Getting All Sellers info from the database
     * @return Seller list
     */
    @GetMapping(value = "/sellers")
    public ResponseEntity<List<Seller>> getAllSellers() {
        log.debug("Getting all Sellers.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.findAll());
    }


    /**
     * Getting Seller By Id
     * @param take Seller Id as parameter
     * @return Seller
     */
    @GetMapping(value = "/sellers/{sellerId}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long sellerId) {
        log.debug("Getting Seller by id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.findById(sellerId));
    }


    /**
     * Getting Seller Shops.
     * @param take Seller Id as parameter
     * @return list of Shop of specified Seller id
     */
    @GetMapping(value = "/sellers/{sellerId}/shops")
    public ResponseEntity<List<Shop>> getSellerShops(@PathVariable Long sellerId) {
        log.debug("Getting Seller Shop by id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.findAllShops(sellerId));
    }


    /**
     *
     * @param This method take new Seller info as parameter to store it in database
     * @return return that new stored Seller info
     */
    @PostMapping(value = "/admin/sellers")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        log.debug("Saving Seller.");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerDAO.save(seller));
    }


    /**
     *
     * @param This method take  Seller id as parameter to store new Shop of that seller in database
     * @return  new stored Shop
     */
    @PostMapping(value = "/admin/sellers/{sellerId}/shops")
    public ResponseEntity<Seller> saveShops(@PathVariable Long sellerId, @RequestBody List<Shop> shops) {
        log.debug("Adding Shops to system - Seller.");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerDAO.saveShops(sellerId, shops));
    }


    /**
     *
     * @param This method take updated seller info as parameter to store it in database
     * @return updated seller info
     */
    @PutMapping(value = "/admin/sellers/{sellerId}")
    public ResponseEntity<Seller> updateSeller(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long sellerId) {
        log.debug("Updating Seller by Shop id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.update(generalDetailVO, sellerId));
    }


    /**
     * Update Shop details of specified seller through seller id
     * @param generalDetailVO general detail value object
     * @param sellerId seller id
     * @param shopId shop id
     * @return updated shop detail
     */
    @PutMapping(value = "/admin/sellers/{sellerId}/shops/{shopId}")
    public ResponseEntity<Shop> updateShopDetails(@RequestBody GeneralDetailVO generalDetailVO, @PathVariable Long sellerId, @PathVariable Long shopId) {
        log.debug("Updating Seller by Shop id.");
        return ResponseEntity.status(HttpStatus.OK).body(sellerDAO.updateShopDetails(generalDetailVO, sellerId, shopId));
    }


    /**
     *
     * @param This method take seller as parameter for delete that seller i.e de-active that seller
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/sellers")
    public ResponseEntity<Object> deleteSeller(@RequestBody Seller seller) {
        log.debug("Deleting Seller.");
        sellerDAO.delete(seller);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param This method take seller id as parameter for delete that seller by seller id
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/sellers/{sellerId}")
    public ResponseEntity<?> deleteSellerById(@PathVariable Long sellerId) {
        log.debug("Deleting Shop Owner by Shop Owner id.");
        sellerDAO.deleteById(sellerId);
        return ResponseEntity.noContent().build();
    }


    /**
     *
     * @param This method take seller id as parameter for delete shop of that seller
     * @param through shopId
     * @return success message as a 204 noContent status with exit code 1
     */
    @DeleteMapping(value = "/admin/sellers/{sellerId}/shops/{shopId}")
    public ResponseEntity<?> deleteShops(@PathVariable Long sellerId, @RequestBody List<Long> shopIds) {
        log.debug("Removing Shops from system - Sellers");
        Seller seller = sellerDAO.deleteShops(sellerId, shopIds);
        return ResponseEntity.noContent().build();
    }
}
