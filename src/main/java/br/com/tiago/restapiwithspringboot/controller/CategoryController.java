package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Category;
import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.CategoryService;
import br.com.tiago.restapiwithspringboot.service.ProductService;
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
    @Operation(summary = "List all the categorys")
    public ResponseEntity<Object> getInfoCategory() {
        List<Category> result = categoryService.getInfoCategory();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idCategory}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idCategory) {
        HashMap<String, Object> result = categoryService.deleteCategory(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findCategory/{idCategory}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long idCategory){
        Category result = categoryService.findCategoryById(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }


    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        Category result = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}
