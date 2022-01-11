package com.fastcampus.investment.service;

import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.exception.NotFoundProductException;
import com.fastcampus.investment.repository.InvestmentRepository;
import com.fastcampus.investment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final ProductRepository productRepository;

    public List<InvestmentDto> getInvestment(Long userId) {
        List<Investment> investmentList = investmentRepository.findByUserId(userId);
        List<InvestmentDto> investmentDtos = new ArrayList<>();
        for (Investment investment : investmentList) {
            InvestmentDto investmentDto = InvestmentDto.toDto(investment);
            investmentDtos.add(investmentDto);
        }
        return investmentDtos;
    }

    public InvestmentDto invest(Long userId, Long productId, Long investAmount) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundProductException("product를 찾을 수 없습니다."));

        InvestmentStatus investmentStatus = InvestmentStatus.INVESTED;
        Long sumOfInvestedAmount = investmentRepository.sumOfInvestedAmount(product);
        if (product.getTotalInvestAmount() < investAmount - sumOfInvestedAmount)
            investmentStatus = InvestmentStatus.FAIL;

        Investment investment = Investment.builder()
                .product(product)
                .investedAmount(investAmount)
                .status(investmentStatus)
                .investedAt(LocalDateTime.now())
                .userId(userId)
                .build();
        Investment savedInvest = investmentRepository.save(investment);
        return InvestmentDto.toDto(savedInvest);
    }
}
