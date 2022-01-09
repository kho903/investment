package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {

    List<Products> findByStartedAtBeforeAndFinishedAtAfter(LocalDateTime startedAt, LocalDateTime finishedAt);
}
