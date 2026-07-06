package com.tidyup.StockService.domain.product.entity;

import com.tidyup.StockService.domain.product.dto.BrandDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BRANDS")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RETAILER_ID")
    private UUID retailerId;

    @Column(name = "BRAND")
    private String brand;

    public Brand(BrandDTO dto) {
        this.retailerId = dto.retailerId();
        this.brand = dto.brand();
    }
}
