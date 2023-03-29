package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer")
@CrossOrigin(value="*")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getInfoCustomers() {
        List<Customer> result = customerService.getInfoCustomers();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
        Customer result = customerService.saveCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idCustomer}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long idCustomer) {
        HashMap<String, Object> result = customerService.deleteCustomer(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @GetMapping(value = "/findCustomer/{idCustomer}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long idCustomer) {
        Customer result = customerService.findCustomerById(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Customer customer) {
        Customer result = customerService.updateCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

}