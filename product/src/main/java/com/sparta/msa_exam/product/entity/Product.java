package com.sparta.msa_exam.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", unique = true)
    private String name;

    @Column(name = "supply_price")
    private Long supplyPrice;

    public static Product create(String name, Long price) {
        return Product.builder()
                .name(name)
                .supplyPrice(price)
                .build();
    }
}
