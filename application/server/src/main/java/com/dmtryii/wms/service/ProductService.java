package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.ProductDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product createProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryService.getCategoryById(categoryId);

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category);

        LOG.info("Product was created");
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product product = getProductById(productId);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        LOG.info("The product from ID {} has been updated", productId);
        return productRepository.save(product);
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        List<Product> products = getAllProduct();

        return products.stream().filter(
                product -> product.getCategory().getId().equals(categoryId)).toList();
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
