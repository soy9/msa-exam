package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long productId;
    private String name;
    private Long supplyPrice;

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .productId(product.getId())
                .name(product.getName())
                .supplyPrice(product.getSupplyPrice())
                .build();
    }
}
