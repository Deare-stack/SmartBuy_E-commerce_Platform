package com.project.smartbuy.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.smartbuy.model.Product;
import com.project.smartbuy.repository.ProductRepository;


@Service

public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //search for all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
