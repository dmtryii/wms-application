package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/")
    private List<Category> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Category> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/add")
    private ResponseEntity<Category> addCategory(@RequestBody Category category) {
        repository.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("delete/{id}")
    private void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
