package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getInfoCustomer() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            return customerRepository.save(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Alguns valores não foram preenchidos e são de carater obrigatório!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long customerId) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer não encontrado!")));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        result.put("result", "Customer: " + customer.get().getFirstNameCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer){
        return customerRepository.findById(idCustomer)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {

        if (customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do Customer é obrigatório na atualização!");
        }

        if (validateCustomer(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public Boolean validateCustomer (Customer customer) {
        if (customer.getFirstNameCustomer() != null &&
                customer.getLastNameCustomer() !=null &&
                customer.getCpfCustomer() != null &&
                customer.getEmailCustomer() != null &&
                customer.getBirthDateCustomer() != null &&
                customer.getPasswordCustomer() != null &&
                customer.getStatusCustomer() != null) {
            return true;
        } else {
            return false;
        }
    }
}
