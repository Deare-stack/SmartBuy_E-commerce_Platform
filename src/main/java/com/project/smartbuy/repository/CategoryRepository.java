package com.project.smartbuy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.smartbuy.model.Category;
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);
}
