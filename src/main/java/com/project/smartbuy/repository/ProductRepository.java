package com.project.smartbuy.repository;
import com.project.smartbuy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.smartbuy.model.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategory(Category category);// Find all products by category
    List<Product> findByNameContainingIgnoreCase(String name);//Fuzzy Search by product's name
}
