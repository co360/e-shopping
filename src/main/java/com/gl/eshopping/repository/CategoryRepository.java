package com.gl.eshopping.repository;

import com.gl.eshopping.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Long> {

}
