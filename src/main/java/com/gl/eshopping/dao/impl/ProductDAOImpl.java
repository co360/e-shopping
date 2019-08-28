package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.ProductNotFoundException;
import com.gl.eshopping.model.Product;
import com.gl.eshopping.repository.ProductRepository;
import com.gl.eshopping.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductDAOImpl implements ProductDAO {
    private ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        log.debug("Getting Products from DAO.");
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        log.debug("Getting Product By Id from DAO.");
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public Product save(Product newProduct) {
        log.debug("Saving Product from DAO.");
        return productRepository.save(newProduct);
    }


    @Override
    public void delete(Product product) {
        log.debug("Deleting Product from DAO.");
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long productId) {
        log.debug("Deleting ProductBy Id from DAO.");
        productRepository.deleteById(productId);
    }
}
