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


    @GetMapping(value ={ " ","/","/products","/home"})
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Getting All Products.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.findAll());
    }


    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        log.debug("Getting Product By Id.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.findById(productId));
    }


    @PostMapping(value = "/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        //product.setId(0l);
        log.debug("Saving Product.");
        productDAO.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDAO.save(product));
    }

    @PutMapping(value = "/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        log.debug("Updating Product.");
        return ResponseEntity.status(HttpStatus.OK).body(productDAO.save(product));
    }






}
