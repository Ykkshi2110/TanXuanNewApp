package com.peter.tanxuannewapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @NotNull(message = "Quantity must not be null!")
    private int quantity;

    @NotNull(message = "Description must not be null!")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @NotNull(message = "Unit must not be null!")
    private String unit;

    private String productImage;

    @NotNull(message = "Price must not be null!")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

}
