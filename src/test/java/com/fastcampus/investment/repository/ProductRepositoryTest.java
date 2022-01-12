package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void makeProductTest() {
        Products product = makeProduct();
        productRepository.save(product);

        Products findProduct = productRepository.findById(5L).get();
        assertThat(findProduct.getTitle()).isEqualTo("첫번째 제품");
    }

    private Products makeProduct() {
        return Products.builder()
                .id(5L)
                .title("첫번째 제품")
                .totalInvestAmount(0L)
                .startedAt(LocalDateTime.now().minusDays(1))
                .finishedAt(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    @Transactional
    void findByStartedAtBeforeAndFinishedAtAfterTest() {
        Products product = makeProduct();
        productRepository.save(product);
        List<Products> result = productRepository.findByStartedAtBeforeAndFinishedAtAfter(LocalDateTime.now(), LocalDateTime.now());

        for (Products products : result) {
            Products getByRepository = productRepository.getById(products.getId());
            assertAll(
                    () -> assertEquals(products.getId(), getByRepository.getId()),
                    () -> assertEquals(products.getTitle(), getByRepository.getTitle()),
                    () -> assertEquals(products.getTotalInvestAmount(), getByRepository.getTotalInvestAmount()),
                    () -> assertEquals(products.getStartedAt(), getByRepository.getStartedAt()),
                    () -> assertEquals(products.getFinishedAt(), getByRepository.getFinishedAt())
            );
        }
    }
}