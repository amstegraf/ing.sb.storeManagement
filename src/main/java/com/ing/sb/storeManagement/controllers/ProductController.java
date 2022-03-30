package com.ing.sb.storeManagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(null);
    }

    @PostMapping
    ResponseEntity<?> save(@Valid @RequestBody final Object productDTO) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("{pid}")
    ResponseEntity<?> update(@PathVariable("pid") String pid, @RequestBody final Object productDTO) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("{pid}")
    ResponseEntity<?> getProduct(@PathVariable("pid") String pid) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{pid}")
    ResponseEntity<?> delete(@PathVariable("pid") String pid) {
        return ResponseEntity.ok(null);
    }
}
