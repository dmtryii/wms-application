package com.dmtryii.wms.service;

import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.model.OrderLine;
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
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product createProduct(Long categoryId, Product productRequest) {
        Category category = categoryService.getCategoryById(categoryId);

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);

        LOG.info("Product was created");
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productRequest) {
        Product product = getProductById(productId);
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());

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
                () -> new NotFoundException("The city was not found by this id")
        );
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        LOG.info("The product from id {} was deleted", productId);
    }
}
