package com.project.smartbuy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categoties")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String name;
}
