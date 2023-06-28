package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.CategoryDTO;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
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
    private ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/{category_id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(name = "category_id") Long id,
                                                   @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    private ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "category_id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
