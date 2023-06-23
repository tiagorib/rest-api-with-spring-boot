package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.lang.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/customer")
@CrossOrigin(value = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/list")
    @Operation(summary = "List all the customers")
    public ResponseEntity<Object> getInfoCustomers() {
        List<Customer> result = customerService.getInfoCustomers();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    @Operation(summary = "Saves a new customer")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
        Customer result = customerService.saveCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idCustomer}")
    @Operation(summary = "Delete an existing customer")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long idCustomer) {
        HashMap<String, Object> result = customerService.deleteCustomer(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findCustomer/{idCustomer}")
    @Operation(summary = "Search for a customer by ID")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long idCustomer){
        Optional<Customer> result = customerService.findCustomerById(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Updates an existing customer")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        Customer result = customerService.updateCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}


