package br.com.tiago.restapiwithspringboot.controller.controller;

import br.com.tiago.restapiwithspringboot.entity.Product;
import br.com.tiago.restapiwithspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin(value = "*")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getInfoProducts() {
        return repository.findAll();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return repository.save(product);
    }

}