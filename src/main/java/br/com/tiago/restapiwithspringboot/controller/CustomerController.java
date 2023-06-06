package br.com.tiago.restapiwithspringboot.controller;


import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.exception.ResponseGenericException;
import br.com.tiago.restapiwithspringboot.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/customer")
@CrossOrigin(value="*")


public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping(value = "/list")
    @Operation(summary = "Listar Customers")
    public ResponseEntity<Object> getInfoCustomers() {
        List<Customer> result = customerService.getInfoCustomers();
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
    @PostMapping(value = "/create")
    @Operation(summary = "Salvar Customers")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
        Customer result = customerService.saveCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @DeleteMapping(value = "/delete/{idCustomer}")
    @Operation(summary = "Excluir Customers")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long idCustomer) { //vou receber uma variavel/ valor atraves da url sem nome parametros usar mesmo nome metodo
        HashMap<String, Object> result = customerService.deleteCustomer(idCustomer); //retornar string
        return ResponseEntity.ok().body(ResponseGenericException.response(result)); //passa a responsabilidade pra service - não é feita exclusao na controller
    }

    @GetMapping(value = "/findCustomer/{idCustomer}")
    @Operation(summary = "Procurar Customers")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long idCustomer) {
        Customer result = customerService.findCustomerById(idCustomer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Atualizar Customers")
    public ResponseEntity<Object> updateProduct(@RequestBody Customer customer) {
       Customer result = customerService.updateCustomer(customer);
        return ResponseEntity.ok().body(ResponseGenericException.response(result));
    }
}