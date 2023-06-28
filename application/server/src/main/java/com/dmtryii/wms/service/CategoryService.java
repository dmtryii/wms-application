package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.CategoryDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        LOG.info("The category of {} was created", category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category = getCategoryById(categoryId);
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        LOG.info("The category of {} has been updated", category.getName());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException("The category was not found by this id")
        );
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        LOG.info("The category from id {} was deleted", categoryId);
    }
}
