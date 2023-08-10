package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.CategoryRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public static final Logger LOG = LoggerFactory.getLogger(Category.class);
    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        LOG.info("The {} category was created", category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, CategoryRequest request) {
        Category category = getCategoryById(categoryId);
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        LOG.info("The category {} was updated", category.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("The category not fount by id: " + categoryId)
        );
    }

    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
        LOG.info("The category from id {} was deleted", categoryId);
    }
}
