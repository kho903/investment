package com.fastcampus.investment.service;

import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.repository.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public List<InvestmentDto> getInvestment(Long userId) {
        List<Investment> investmentList = investmentRepository.findByUserId(userId);
        List<InvestmentDto> investmentDtos = new ArrayList<>();
        for (Investment investment : investmentList) {
            InvestmentDto investmentDto = InvestmentDto.toDto(investment);
            investmentDtos.add(investmentDto);
        }
        return investmentDtos;
    }
}
