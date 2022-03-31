package com.ing.sb.storeManagement.controllers;

import com.ing.sb.storeManagement.dtos.ProductDTO;
import com.ing.sb.storeManagement.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping
    ResponseEntity<?> getAllProducts(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "startPrice", required = false) Integer startPrice,
            @RequestParam(value = "endPrice", required = false) Integer endPrice
    ) {
        ProductDTO filters = new ProductDTO();
        filters.setTitle(title);
        filters.setStartPrice(startPrice);
        filters.setEndPrice(endPrice);

        return ResponseEntity.ok(productsService.getAllProducts(filters));
    }

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody final ProductDTO productDTO) {
        return ResponseEntity.ok(productsService.save(
                productsService.convertToEntity(productDTO)
        ));
    }

    @PutMapping("{pid}")
    ResponseEntity<?> update(@PathVariable("pid") String pid, @RequestBody final ProductDTO productDTO) {
        return ResponseEntity.ok(productsService.update(
                productsService.convertToEntity(productDTO)
        ));
    }

    @GetMapping("{pid}")
    ResponseEntity<?> getProduct(@PathVariable("pid") String pid) {
        return ResponseEntity.ok(productsService.getProduct(pid));
    }

    @DeleteMapping("{pid}")
    ResponseEntity<?> delete(@PathVariable("pid") String pid) {
        productsService.delete(pid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
