package com.fastcampus.investment.service;

import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.repository.InvestmentRepository;
import com.fastcampus.investment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InvestmentRepository investmentRepository;

    @Test
    @Transactional
    public void getValidProductsTest() throws Exception {
        Products newProduct = makeProduct();
        productRepository.save(newProduct);

        List<ProductDto> validProducts = productService.getValidProducts();

        for (ProductDto products : validProducts) {
            Products getByRepository = productRepository.getById(products.getId());
            ProductDto productDto = ProductDto.toDto(getByRepository);
            // have to refactoring -> investedAmount, investedCount 를 이렇게 처리하는 것이 맞는 것일까..?
            productDto.setInvestedAmount(investmentRepository.sumOfInvestedAmount(getByRepository));
            productDto.setInvestedCount(investmentRepository.countByProductAndInvestmentStatus(getByRepository, InvestmentStatus.INVESTED));

            assertAll(
                    () -> assertEquals(products.getId(), productDto.getId()),
                    () -> assertEquals(products.getTitle(), productDto.getTitle()),
                    () -> assertEquals(products.getTotalInvestAmount(), productDto.getTotalInvestAmount()),
                    () -> assertEquals(products.getInvestedAmount(), productDto.getInvestedAmount()),
                    () -> assertEquals(products.getInvestedCount(), productDto.getInvestedCount()),
                    () -> assertEquals(products.getStartedAt(), productDto.getStartedAt()),
                    () -> assertEquals(products.getFinishedAt(), productDto.getFinishedAt())
            );
        }
    }

    private Products makeProduct() {
        return Products.builder()
                .id(5L)
                .title("첫번째 제품")
                .totalInvestAmount(0L)
                .investedCount(0L)
                .investedAmount(0L)
                .startedAt(LocalDateTime.now().minusDays(1))
                .finishedAt(LocalDateTime.now().plusDays(1))
                .build();
    }

}