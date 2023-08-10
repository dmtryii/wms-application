package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.CategoryDTO;
import com.dmtryii.wms.dto.request.CategoryRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Category;
import com.dmtryii.wms.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @GetMapping
    private ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<CategoryDTO> categories = categoryService.getAllCategory()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    private ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "category_id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(
                map(category),
                HttpStatus.OK
        );
    }

    @PostMapping
    private ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryRequest request,
                                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Category category = categoryService.createCategory(request);
        return new ResponseEntity<>(
                map(category),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "category_id") Long categoryId,
                                                      @RequestBody @Valid CategoryRequest request,
                                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        Category category = categoryService.updateCategory(categoryId, request);
        return new ResponseEntity<>(
                map(category),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{category_id}")
    private ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CategoryDTO map(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
