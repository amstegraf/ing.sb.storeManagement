package com.ing.sb.storeManagement.services;

import com.ing.sb.storeManagement.entities.Product;
import com.ing.sb.storeManagement.exceptions.ResourceNotFoundException;
import com.ing.sb.storeManagement.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return null;
    }

    public Product getProduct(String id) {
        if (!productsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }

        return null;
    }

    public Product save(Product product) {
        return null;
    }

    public Product update(Product product) {
        Product productExisting = getProduct(product.getId());

        BeanUtils.copyProperties(product, productExisting);

        return save(productExisting);
    }

    public boolean delete(String id) {
        Product productExisting = getProduct(id);

        return delete(productExisting);
    }

    public boolean delete(Product product) {
        return false;
    }
}
