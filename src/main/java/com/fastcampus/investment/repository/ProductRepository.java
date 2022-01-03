package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
