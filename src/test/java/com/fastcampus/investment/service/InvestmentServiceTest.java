package com.fastcampus.investment.service;

import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.entity.Investment;
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
class InvestmentServiceTest {

    @Autowired
    InvestmentService investmentService;
    @Autowired
    InvestmentRepository investmentRepository;

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    public void getInvestmentTest() throws Exception {
        Products products = makeProduct();
        Investment investment = Investment.builder()
                .id(1L)
                .userId(1L)
                .status(InvestmentStatus.INVESTED)
                .investedAt(LocalDateTime.now())
                .investedAmount(0L)
                .product(products)
                .build();

        Investment investment2 = Investment.builder()
                .id(2L)
                .userId(1L)
                .status(InvestmentStatus.INVESTED)
                .investedAt(LocalDateTime.now())
                .investedAmount(0L)
                .product(products)
                .build();

        productRepository.save(products);
        investmentRepository.save(investment);
        investmentRepository.save(investment2);

        List<InvestmentDto> getInvestmentList = investmentService.getInvestment(1L);
        assertAll(
                () -> assertEquals(getInvestmentList.size(), 2),
                () -> assertEquals(getInvestmentList.get(0).getId(), investment.getId()),
                () -> assertEquals(getInvestmentList.get(1).getId(), investment2.getId()),
                () -> assertEquals(getInvestmentList.get(0).getStatus(), investment.getStatus()),
                () -> assertEquals(getInvestmentList.get(1).getStatus(), investment2.getStatus()),
                () -> assertEquals(getInvestmentList.get(0).getInvestedAmount(), investment.getInvestedAmount()),
                () -> assertEquals(getInvestmentList.get(1).getInvestedAmount(), investment2.getInvestedAmount()),
                () -> assertEquals(getInvestmentList.get(0).getInvestedAt(), investment.getInvestedAt()),
                () -> assertEquals(getInvestmentList.get(1).getInvestedAt(), investment2.getInvestedAt()),
                () -> assertEquals(getInvestmentList.get(0).getProduct().getId(), investment.getProduct().getId()),
                () -> assertEquals(getInvestmentList.get(1).getProduct().getId(), investment2.getProduct().getId()),
                () -> assertEquals(getInvestmentList.get(0).getProduct().getTitle(), investment.getProduct().getTitle()),
                () -> assertEquals(getInvestmentList.get(1).getProduct().getTitle(), investment2.getProduct().getTitle()),
                () -> assertEquals(getInvestmentList.get(0).getProduct().getStartedAt(), investment.getProduct().getStartedAt()),
                () -> assertEquals(getInvestmentList.get(1).getProduct().getStartedAt(), investment2.getProduct().getStartedAt()),
                () -> assertEquals(getInvestmentList.get(0).getProduct().getFinishedAt(), investment.getProduct().getFinishedAt()),
                () -> assertEquals(getInvestmentList.get(1).getProduct().getFinishedAt(), investment2.getProduct().getFinishedAt())
        );

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
}