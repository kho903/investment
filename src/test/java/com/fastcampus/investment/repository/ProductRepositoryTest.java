package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void repositoryTest() {
        Products product = new Products();
        product.setTitle("첫번째 제품");
        product.setId(1L);

        productRepository.save(product);

        Products findProduct = productRepository.findById(1L).get();
        assertThat(findProduct.getTitle()).isEqualTo("첫번째 제품");
    }
}