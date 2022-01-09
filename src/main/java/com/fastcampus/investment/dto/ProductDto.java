package com.fastcampus.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String title;

    private Long totalInvestingAmount;

    private Long investedCount;

    private Long investedAmount;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;
}
