package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    private ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    private ResponseEntity<Category> getCategoryById(@PathVariable(name = "category_id") Long id) {

        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Category> createCategory(@RequestBody Category categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(name = "category_id") Long categoryId,
                                                   @RequestBody Category categoryRequest) {
        Category category = categoryService.updateCategory(categoryId, categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    private ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
