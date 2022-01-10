package com.fastcampus.investment.api;

import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.entity.ProductResponse;
import com.fastcampus.investment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Apis {

    private final ProductService productService;

    @GetMapping("/product")
    public ProductResponse<?> getAllProduct() {
        List<ProductDto> validProducts = productService.getValidProducts();
        return new ProductResponse<>(validProducts, HttpStatus.OK);
    }
}
