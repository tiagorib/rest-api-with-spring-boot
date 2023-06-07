package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(value = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/list")
    @Operation(summary = "List all the products")
    public ResponseEntity<Object> getInfoProducts() {
        List<Product> result = productService.getInfoProducts();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    @Operation(summary = "Save a new product")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        Product result = productService.saveProduct(product);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idProduct}")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idProduct) {
        HashMap<String, Object> result = productService.deleteProduct(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findProduct/{idProduct}")
    @Operation(summary = "Find product by ID")
    public ResponseEntity<Object> getProductById(@PathVariable Long idProduct){
        Product result = productService.findProductById(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }


    @PutMapping(value = "/update")
    @Operation(summary = "Modify product informations")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        Product result = productService.updateProduct(product);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}