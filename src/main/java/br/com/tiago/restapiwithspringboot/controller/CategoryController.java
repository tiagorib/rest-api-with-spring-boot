package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Category;
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
    @CrossOrigin(value = "*")
    public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @GetMapping(value = "/list")
        @Operation(summary = "This method lists all categories")
        public ResponseEntity<Object> getAllCategories() {
            List<Category> result = categoryService.getAllCategories();
            return ResponseEntity.ok().body(ResponseGenericException.response(result));
        }

        @PostMapping(value = "/create")
        @Operation(summary = "This method creates a new category")
        public ResponseEntity<Object> createCategory(@RequestBody Category category) {
            Category result = categoryService.createCategory(category);
            return ResponseEntity.ok().body(ResponseGenericException.response(result));
        }

        @DeleteMapping(value = "/delete/{idCategory}")
        @Operation(summary = "This method deletes a category")
        public ResponseEntity<Object> deleteCategory(@PathVariable Long idCategory) {
            HashMap<String, Object> result = categoryService.deleteCategory(idCategory);
            return ResponseEntity.ok().body(ResponseGenericException.response(result));
        }

        @GetMapping(value = "/findCategory/{idCategory}")
        @Operation(summary = "This method is used to find a category")
        public ResponseEntity<Object> getCategoryById(@PathVariable Long idCategory){
            Category result = categoryService.getCategoryById(idCategory);
            return ResponseEntity.ok().body(ResponseGenericException.response(result));
        }

        @PutMapping(value = "/update")
        @Operation(summary = "This method is used to update a category")
        public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
            Category result = categoryService.updateCategory(category);
            return ResponseEntity.ok().body(ResponseGenericException.response(result));
        }

    }


