package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.service.CustomerService;
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
    public Customer save(@RequestBody Customer product){
        return service.save(product);
    }

    @PutMapping(value = "/update")
    public Customer update(@RequestBody Customer product){
        return service.update(product);
    }

    @GetMapping(value = "/list")
    public List<Customer> list(){
        return service.list();
    }

    @GetMapping(value = "/find/{id}")
    public Optional<Customer> findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

}
