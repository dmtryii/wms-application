package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.ProductDTO;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

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

        List<Product> products = productService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("{category_id}/category")
    public ResponseEntity<Product> createProduct(@PathVariable(name = "category_id") Long categoryId,
                                                 @RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(categoryId, productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("{product_id}")
    public ResponseEntity<Product> updateProductById(@PathVariable(name = "product_id") Long productId,
                                                 @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{product_id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(name = "product_id") Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
