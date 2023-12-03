package com.example.amazonclone.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Product id must not be empty")
    private Integer productid;
    @NotNull(message = "Merchant id must not be empty")
    private Integer merchantid;
    @NotNull(message = "stock must not be empty")
    @Column(columnDefinition = "int not null")
    private int stock;
}
