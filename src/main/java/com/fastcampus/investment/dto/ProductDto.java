package com.fastcampus.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
