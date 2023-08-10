package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.ProductRequest;
import com.dmtryii.wms.dto.request.ProductUpdateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(Product.class);
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product createProduct(ProductRequest request) {
        Category category = categoryService.getCategoryById(request.getCategoryId());

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .category(category)
                .build();

        LOG.info("Product was created");
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = getProductById(productId);
        Category category = categoryService.getCategoryById(request.getCategoryId());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        LOG.info("The product from ID {} has been updated", productId);
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("The product not fount by id: " + productId)
        );
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        LOG.info("The product from id {} was deleted", productId);
    }
}
