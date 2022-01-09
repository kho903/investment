package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
