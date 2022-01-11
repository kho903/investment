package com.fastcampus.investment.service;

import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.repository.InvestmentRepository;
import com.fastcampus.investment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InvestmentRepository investmentRepository;

    public List<ProductDto> getValidProducts() {
        LocalDateTime now = LocalDateTime.now();
        List<Products> productsList = productRepository.findByStartedAtBeforeAndFinishedAtAfter(now, now);
        List<ProductDto> result = new ArrayList<>();

        for (Products products : productsList) {
            ProductDto productDto = ProductDto.toDto(products);
            productDto.setInvestedAmount(investmentRepository.sumOfInvestedAmount(products));
            productDto.setInvestedCount(investmentRepository.countByProductAndStatus(products, InvestmentStatus.INVESTED));
            result.add(productDto);
        }

        return result;
    }
}
