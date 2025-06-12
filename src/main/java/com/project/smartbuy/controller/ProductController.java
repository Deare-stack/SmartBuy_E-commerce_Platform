package com.project.smartbuy.controller;
import java.util.List;
import com.project.smartbuy.model.Product;
import com.project.smartbuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")

public class ProductController {
    @Autowired
    private ProductService productService;

    // constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
