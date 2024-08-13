package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class OrderResponse {
    private Long orderId;
    private String name;
    private List<Long> productIds;

    // Order -> OrderResponse 변경 Static 메서드
    public static OrderResponse toDto(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .name(order.getName())
                .productIds(order.getProductList().stream().map(OrderProduct::getProductId).collect(Collectors.toList()))
                .build();
    }
}
