package com.gl.eshopping.dao.impl;

import com.gl.eshopping.exception.ProductFoundException;
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
        log.debug("Getting Food Items from DAO.");
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        log.debug("Getting Food Item By Id from DAO.");
        return productRepository.findById(productId).orElseThrow(() -> new ProductFoundException(productId));
    }

    @Override
    public Product save(Product newProduct) {
        log.debug("Saving Food Item from DAO.");
        return productRepository.save(newProduct);
    }

    public Product update(Product product, Long productId) {
        log.debug("Updating Food Item from DAO.");
        return null;
    }

    @Override
    public void delete(Product product) {
        log.debug("Deleting Food Item from DAO.");
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long productId) {
        log.debug("Deleting Food Item By Id from DAO.");
        productRepository.deleteById(productId);
    }
}
