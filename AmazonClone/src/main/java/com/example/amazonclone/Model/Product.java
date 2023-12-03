package com.example.amazonclone.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4,message = "Name should be at least 4 characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;
    @NotNull(message = "Price should not be empty")
    @Positive(message = "Price should be a positive number")
    @Column(columnDefinition = "double not null")
    private double price;
    @NotNull(message = "Category id should not be empty")
    private Integer categoryID;
}
