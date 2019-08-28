package com.gl.eshopping.dao.impl;

import com.gl.eshopping.dao.CategoryDAO;
import com.gl.eshopping.exception.CategoryNotFoundException;
import com.gl.eshopping.exception.ProductNotFoundException;
import com.gl.eshopping.model.Category;
import com.gl.eshopping.model.Product;
import com.gl.eshopping.repository.CategoryRepository;
import com.gl.eshopping.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryDAOImpl implements CategoryDAO {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryDAOImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getCategoryProducts(Long categoryId) {
        log.debug("Get all Category Products.");
        Category category = findById(categoryId);
        return category.getProducts().stream().collect(Collectors.toList());
    }

    @Override
    public Product getCategoryProductById(Long categoryId, Long productId) {
        log.debug("Getting Category product by id.");
        Category category = findById(categoryId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        if (!product.getCategory().equals(category)) {
            throw new ProductNotFoundException(productId);
        }
        return product;
    }

    @Override
    public List<Category> findAll() {
        log.debug("Returning Category(s) from DAO");
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long categoryId) {
        log.debug("Returning Category(s) by id from DAO");
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    public Category save(Category newCategory) {
        log.debug("Saving Category(s) from DAO");
        return categoryRepository.save(newCategory);
    }

    @Override
    public void delete(Category category) {
        log.debug("Deleting Category from DAO");
       categoryRepository.delete(category);

    }

    @Override
    public void deleteById(Long categoryId) {
        log.debug("Deleting Category by Id from DAO");
        categoryRepository.deleteById(categoryId);

    }
}
