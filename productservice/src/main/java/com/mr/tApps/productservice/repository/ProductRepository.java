package com.mr.tApps.productservice.repository;

import com.mr.tApps.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
