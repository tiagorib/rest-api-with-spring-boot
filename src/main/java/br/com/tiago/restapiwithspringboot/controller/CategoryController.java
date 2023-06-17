package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Category;
import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin(value="*")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list")
    @Operation(summary = "List all the categories")
    public ResponseEntity<Object> getInfoCategories() {
        List<Category> result = categoryService.getInfoCategories();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    @Operation(summary = "Save a new category")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idCategory}")
    @Operation(summary = "Delete an existing category")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long idCategory) {
        HashMap<String, Object> result = categoryService.deleteCategory(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findCategory/{idCategory}")
    @Operation(summary = "Search for a category by ID")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long idCategory){
        Category result = categoryService.findCategoryById(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Updates an existing category")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        Category result = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }


}