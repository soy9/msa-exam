package com.sparta.msa_exam.product;

import com.sparta.msa_exam.product.dto.CreateProductRequest;
import com.sparta.msa_exam.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final String serverPort;

    public ProductController(ProductService productService, @Value("${server.port}") String serverPort) {
        this.productService = productService;
        this.serverPort = serverPort;
    }

    /**
     * 1. 상품 추가 API
     * */
    @PostMapping
    public Boolean addProduct(@RequestBody CreateProductRequest productDto) {
        productService.addProduct(productDto);
        return true;
    }


    /**
     * 2. 상품 목록 조회 API
     * */
    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductList() {
        return createResponse(ResponseEntity.ok(productService.getProductList()));
    }

    // Response Header 에 `Server-Port` 룰 추가해주는 Generic 함수입니다.
    public <T> ResponseEntity<T> createResponse(ResponseEntity<T> response) {
        HttpHeaders headers = HttpHeaders.writableHttpHeaders(response.getHeaders()); // 인자로 받은 헤더의 정보를 수정할 수 있도록 불러옵니다.
        headers.add("Server-Port", serverPort); // Response Header 에 Server-Port 키값을 추가합니다.
        return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode()); //인자로 받은 값에 수정한 헤더만 적용하여 응답합니다.
    }
}
