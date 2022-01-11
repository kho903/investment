package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.InvestmentStatus;
import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    Long countByProductAndStatus(Products products, InvestmentStatus investmentStatus);

    @Query("select COALESCE(sum(i.investedAmount), 0) from Investment i where i.product.id = :#{#product.id} and i.status = 'INVESTED'")
    Long sumOfInvestedAmount(Products product);

    List<Investment> findByUserId(Long userId);
}
