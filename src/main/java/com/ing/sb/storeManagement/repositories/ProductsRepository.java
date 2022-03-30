package com.ing.sb.storeManagement.repositories;

import com.ing.sb.storeManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
