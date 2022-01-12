package com.fastcampus.investment.dto;

import com.fastcampus.investment.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String title;

    private Long totalInvestAmount;

    private Long investedCount;

    private Long investedAmount;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    public static ProductDto toDto(Products products) {
        return ProductDto.builder()
                .id(products.getId())
                .title(products.getTitle())
                .totalInvestAmount(products.getTotalInvestAmount())
                .startedAt(products.getStartedAt().toLocalDate())
                .finishedAt(products.getFinishedAt().toLocalDate())
                .build();
    }
}
