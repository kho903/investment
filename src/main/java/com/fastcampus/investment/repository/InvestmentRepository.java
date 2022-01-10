package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    Long countByProductAndInvestmentStatus(Products products, InvestmentStatus investmentStatus);

    @Query("select COALESCE(sum(i.investedAmount), 0) from Investment i where i.product.id = :#{#product.id}")
    Long sumOfInvestedAmount(Products product);
}
