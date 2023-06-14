package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.repository.CategoryRepository;
import com.dmtryii.wms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("{category_id}/category")
        public ResponseEntity<List<Product>> getAllProductByCategoryId(
            @PathVariable(name = "category_id") Long categoryId) {

        if(!categoryRepository.existsById(categoryId)) {
            // Exception
        }

        List<Product> products = productRepository.findByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("{category_id}/category")
    public ResponseEntity<Product> createProduct(@PathVariable(name = "category_id") Long categoryId,
            @RequestBody Product productRequest) {

        Product product = categoryRepository.findById(categoryId).map(category -> {
            productRequest.setCategory(category);
            return productRepository.save(productRequest);
        }).orElseThrow();

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}
