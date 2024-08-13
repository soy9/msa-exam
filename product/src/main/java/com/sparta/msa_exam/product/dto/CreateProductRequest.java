package com.sparta.msa_exam.product.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private Long supplyPrice;
}
