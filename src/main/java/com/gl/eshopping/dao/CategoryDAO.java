package com.gl.eshopping.dao;


import com.gl.eshopping.model.Category;
import com.gl.eshopping.model.Product;

import java.util.List;

public interface CategoryDAO extends CrudDAO<Category,Long> {

    List<Product> getCategoryProducts(Long categoryId);


    Product getCategoryProductById(Long categoryId, Long productId);




}
