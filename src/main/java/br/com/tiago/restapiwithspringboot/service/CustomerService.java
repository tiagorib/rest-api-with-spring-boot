package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Customer> getInfoCustomer() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        customer.setPasswordCustomer(encoder.encode(customer.getPasswordCustomer()));
        return customerRepository.saveAndFlush(customer);
    }

    public Customer findCustomerById(Long idCustomer){
        return customerRepository.findById(idCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    public Customer updateCustomer(Customer customer){
        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O ID do cliente é obrigatório na atualização!");
        }
        encryptPassword(customer);
        return customerRepository.saveAndFlush(customer);
    }

    public HashMap<String, Object> deleteCustomer(Long idCustomer){
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findById(idCustomer)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado!")));
        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "Cliente: " + customer.get().getFirstNameCustomer() + " excluido com sucesso!");
        return result;

    }

    public void encryptPassword (Customer customer) {
        Optional<Customer> oldOBJ = customerRepository.findById(customer.getIdCustomer());

        if (!customer.getPasswordCustomer().equals(oldOBJ.get().getPasswordCustomer())) {
            customer.setPasswordCustomer(encoder.encode(customer.getPasswordCustomer()));
        }
    }

}
