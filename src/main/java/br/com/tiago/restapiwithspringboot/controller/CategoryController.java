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
@RequestMapping(value = "/api/category")
@CrossOrigin(value = "*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Operation(summary = "Lista todas as Categorias")
    public ResponseEntity<Object> getInfoCategories() {
        List<Category> result = categoryService.getInfoCategories();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PostMapping("/save")
    @Operation(summary = "Salva as Categorias")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping("/delete/{idCategory}")
    @Operation(summary = "Deleta as Categorias usando parâmetro")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long idCategory) {
        HashMap<String, Object> result = categoryService.deleteCategory(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping("/find/{idCategory}")
    @Operation(summary = "Pesquisa as Categorias usando parâmetro")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long idCategory) {
        Category result = categoryService.findCategoryById(idCategory);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping("/update")
    @Operation(summary = "Atualiza as Categorias")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        Category result = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}
