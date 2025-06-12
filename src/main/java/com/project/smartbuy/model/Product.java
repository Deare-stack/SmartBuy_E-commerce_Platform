package com.project.smartbuy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
