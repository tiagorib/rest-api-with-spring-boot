package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.ProductService;
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
    public ResponseEntity<Object> getInfoProducts() { // aqui devolve a informação pro front // a solicitação é feita para o endpoint, o endpoint transfere pra camada service e a camada service faz as coisas acontecer e devolve a resposta pra controller
        List<Product> result = productService.getInfoProducts();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        Product result = productService.saveProduct(product);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idProduct}") // Aqui o idProduct é o id do meu item, no postman, se eu colocar <http://localhost:8080/api/v1/product/delete/1> ele vai deletar o item 1
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idProduct) {
        HashMap<String, Object> result = productService.deleteProduct(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findProduct/{idProduct}")
    public ResponseEntity<Object> getProductById(@PathVariable Long idProduct){
        Product result = productService.findProductById(idProduct);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }


    @PutMapping(value = "/update")                                                  //metodo put altera toodo o objeto
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        Product result = productService.updateProduct(product);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}