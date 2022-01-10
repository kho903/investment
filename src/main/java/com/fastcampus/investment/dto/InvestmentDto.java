package com.fastcampus.investment.dto;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDto {

    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    private Long investedAmount;

    private InvestmentStatus investmentStatus;

    private LocalDateTime investedAt;

    private Long userId;

    public static InvestmentDto toDto(Investment investment) {
        return InvestmentDto.builder()
                .id(investment.getId())
                .product(investment.getProduct())
                .investedAmount(investment.getInvestedAmount())
                .investmentStatus(investment.getInvestmentStatus())
                .investedAt(investment.getInvestedAt())
                .userId(investment.getUserId())
                .build();
    }
}
