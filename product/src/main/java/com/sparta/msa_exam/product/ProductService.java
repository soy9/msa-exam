package com.sparta.msa_exam.product;

import com.sparta.msa_exam.product.dto.CreateProductRequest;
import com.sparta.msa_exam.product.dto.ProductDto;
import com.sparta.msa_exam.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(CreateProductRequest productDto) {
        productRepository.save(Product.create(productDto.getName(), productDto.getSupplyPrice()));
    }

    public List<ProductDto> getProductList() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }
}
