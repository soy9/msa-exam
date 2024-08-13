package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.dto.AddProductRequest;
import com.sparta.msa_exam.order.dto.CreateOrderRequest;
import com.sparta.msa_exam.order.dto.OrderResponse;
import com.sparta.msa_exam.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final String serverPort;

    public OrderController(OrderService orderService, @Value("${server.port}") String serverPort) {
        this.orderService = orderService;
        this.serverPort = serverPort;
    }

    /**
     * 3. 주문 추가 API
     * */
    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
        return createResponse(ResponseEntity.ok(true));
    }

    /**
     * 4. 주문에 상품을 추가하는 API
     * */
    @PutMapping("/{orderId}")
    public ResponseEntity<Boolean> update(@PathVariable("orderId") Long orderId, @RequestBody AddProductRequest dto) {
        orderService.addProductToOrder(orderId, dto);
        return createResponse(ResponseEntity.ok(true));
    }

    /**
     * 5. 주문 단건 조회 API
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> readOne(@PathVariable("orderId") Long orderId) {
        return createResponse(ResponseEntity.ok(orderService.readOne(orderId)));
    }

    // Response Header 에 `Server-Port` 룰 추가해주는 Generic 함수
    public <T> ResponseEntity<T> createResponse(ResponseEntity<T> response) {
        HttpHeaders headers = HttpHeaders.writableHttpHeaders(response.getHeaders()); // 인자로 받은 헤더의 정보를 수정할 수 있도록 불러옵니다.
        headers.add("Server-Port", serverPort); // Response Header 에 Server-Port 키값을 추가합니다.
        return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode()); //인자로 받은 값에 수정한 헤더만 적용하여 응답합니다.
    }
}
