package com.fastcampus.investment.dto;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private InvestmentStatus status;

    private LocalDateTime investedAt;

    private Long userId;

    public static InvestmentDto toDto(Investment investment) {
        return InvestmentDto.builder()
                .id(investment.getId())
                .product(investment.getProduct())
                .investedAmount(investment.getInvestedAmount())
                .status(investment.getStatus())
                .investedAt(investment.getInvestedAt())
                .userId(investment.getUserId())
                .build();
    }
}
