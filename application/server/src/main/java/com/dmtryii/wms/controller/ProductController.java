package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.ProductDTO;
import com.dmtryii.wms.dto.request.ProductRequest;
import com.dmtryii.wms.dto.request.ProductUpdateRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> products = productService.getAllProduct()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "product_id") Long productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(
                map(product),
                HttpStatus.OK
        );
    }

    @GetMapping("/categories/{category_id}")
    public ResponseEntity<List<ProductDTO>> getAllProductByCategoryId(
            @PathVariable(name = "category_id") Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategoryId(categoryId)
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductRequest request,
                                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(
                map(product),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable(name = "product_id") Long productId,
                                                     @RequestBody @Valid ProductUpdateRequest request,
                                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        Product product = productService.updateProduct(productId, request);
        return new ResponseEntity<>(
                map(product),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(name = "product_id") Long productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ProductDTO map(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
