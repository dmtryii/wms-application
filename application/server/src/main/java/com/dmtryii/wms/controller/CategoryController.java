package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    private ResponseEntity<List<Category>> getAll() {

        List<Category> categories = repository.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    private ResponseEntity<Category> getById(@PathVariable(name = "category_id") Long id) {

        Category category = repository.findById(id).orElseThrow();

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Category> createCategory(@RequestBody Category category) {

        Category _category = repository.save(new Category(
                category.getName(),
                category.getDescription()
        ));

        return new ResponseEntity<>(_category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{category_id}")
    private ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "category_id") Long id) {
        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
