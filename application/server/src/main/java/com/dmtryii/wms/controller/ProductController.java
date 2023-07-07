package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "product_id") Long productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("{category_id}/category")
    public ResponseEntity<List<Product>> getAllProductByCategoryId(
            @PathVariable(name = "category_id") Long categoryId) {

        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("{category_id}/category")
    public ResponseEntity<Product> createProduct(@PathVariable(name = "category_id") Long categoryId,
                                                 @RequestBody Product productRequest) {
        Product product = productService.createProduct(categoryId, productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("{product_id}")
    public ResponseEntity<Product> updateProductById(@PathVariable(name = "product_id") Long productId,
                                                 @RequestBody Product productRequest) {
        Product product = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{product_id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(name = "product_id") Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
