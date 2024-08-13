package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.ProductClient;
import com.sparta.msa_exam.order.dto.CreateOrderRequest;
import com.sparta.msa_exam.order.dto.AddProductRequest;
import com.sparta.msa_exam.order.dto.OrderResponse;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static com.sparta.msa_exam.order.entity.OrderProduct.create;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    /**
     * 3. 주문 추가 API
     */
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        orderRepository.save(Order.create(createOrderRequest.getName()));
    }

    /**
     * 4. 주문에 상품을 추가하는 로직
     */
    @Transactional
    public void addProductToOrder(Long orderId, AddProductRequest dto) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        // 필수 과제 - 주문에 상품을 추가하는 API 만들 때 아래와 같이 구성해보세요.
        productService.getProductList().stream() // 상품 목록 조회
                .filter(item -> Objects.equals(item.getProductId(), dto.getProductId())).findAny()  // 상품 목록 중에 request.getProductId() 와 Id 가 일치하는 상품이 있는지 검색
                .ifPresent(product -> order.addProduct(create(order, product.getProductId()))); // 상품이 있다면 주문 Entity 의 addProduct 메서드로
    }

    /**
     * 5. 주문 단건 조회 로직
     */
    // @Cacheable(cacheNames = "order_cache")
    public OrderResponse readOne(Long orderId) {

        return orderRepository.findById(orderId)
                .map(OrderResponse::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
