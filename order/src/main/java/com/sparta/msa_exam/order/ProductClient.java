package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.dto.ProductDto;
import com.sparta.msa_exam.order.service.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient extends ProductService {
    @GetMapping("/products")
    List<ProductDto> getProductList();
}
