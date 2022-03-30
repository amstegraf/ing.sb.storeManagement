package com.ing.sb.storeManagement.repositories;

import com.ing.sb.storeManagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductsRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
}
