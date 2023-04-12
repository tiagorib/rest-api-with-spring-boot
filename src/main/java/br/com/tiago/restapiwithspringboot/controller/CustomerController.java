package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(value = "/save")
    @Operation(summary = "This method creates a new customer")
    public Customer save(@RequestBody Customer product){
        return service.save(product);
    }

    @PutMapping(value = "/update")
    @Operation(summary = "This method is used to update a customer")
    public Customer update(@RequestBody Customer product){
        return service.update(product);
    }

    @GetMapping(value = "/list")
    @Operation(summary = "This method lists all customers")
    public List<Customer> list(){
        return service.list();
    }

    @GetMapping(value = "/find/{id}")
    @Operation(summary = "This method is used to find a customer")
    public Optional<Customer> findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "This method deletes a customer")
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
