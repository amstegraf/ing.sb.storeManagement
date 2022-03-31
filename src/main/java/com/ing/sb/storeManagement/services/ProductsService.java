package com.ing.sb.storeManagement.services;

import com.ing.sb.storeManagement.dtos.ProductDTO;
import com.ing.sb.storeManagement.entities.Product;
import com.ing.sb.storeManagement.exceptions.ResourceNotFoundException;
import com.ing.sb.storeManagement.repositories.ProductsRepository;
import com.ing.sb.storeManagement.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService implements Convertable<Product, ProductDTO> {
    private final ProductsRepository productsRepository;

    public List<Product> getAllProducts(ProductDTO filters) {
        Specification<Product> productSpecifications = ProductSpecification
                .filterByTitle(filters.getTitle())
                .and(ProductSpecification.filterByStartPrice(filters.getStartPrice()))
                .and(ProductSpecification.filterByEndPrice(filters.getEndPrice()));

        return productsRepository.findAll(productSpecifications);
    }

    public Product getProduct(String id) {
        return productsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    public Product update(Product product) {
        Product productExisting = getProduct(product.getId());

        BeanUtils.copyProperties(product, productExisting);

        return save(productExisting);
    }

    public boolean delete(String id) {
        Product productExisting = null;

        // as per RFC there is no need to return an error on refresh
        try {
            productExisting = getProduct(id);
        } catch (ResourceNotFoundException e) {
            return true;
        }

        return delete(productExisting);
    }

    public boolean delete(Product product) {
        productsRepository.delete(product);

        return true;
    }

    @Override
    public Product convertToEntity(ProductDTO dto) {
        Product product = new Product();

        BeanUtils.copyProperties(dto, product);

        return product;
    }

    @Override
    public ProductDTO convertToDTO(Product entity) {
        ProductDTO productDTO = new ProductDTO();

        BeanUtils.copyProperties(entity, productDTO);

        return productDTO;
    }
}
