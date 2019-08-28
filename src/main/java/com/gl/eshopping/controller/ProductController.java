package com.gl.eshopping.controller;

import com.gl.eshopping.dao.ProductDAO;
import com.gl.eshopping.dto.ProductDTO;
import com.gl.eshopping.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/eshopping")
public class ProductController {

    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    /**
     * Getting All Products
     * @return all available products
     */
    @GetMapping(value ={ " ","/","/products","/home"})
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Getting All Products.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.findAll());
    }


    /**
     * Getting Product By Id
     * @param This method take Product Id as parameter
     * @return Product
     */
    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        log.debug("Getting Product By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.findById(productId));
    }


    /**
     *
     * @param This method take new product as parameter to store it
     * @return return info of that new stored product
     */
    @PostMapping(value = "/admin/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        //product.setId(0l);
        log.debug("Saving Product.");
        productDAO.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDAO.save(product));
    }


    /**
     *
     * @param This method take updated product info  as parameter to store it database
     * @return updated product info
     */
    @PutMapping(value = "/admin/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        log.debug("Updating Product.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.save(product));
    }

}
