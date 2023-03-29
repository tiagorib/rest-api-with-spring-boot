package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.HashMap;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository CustomerRepository;

    public List<Customer> getInfoCustomers() {
        return CustomerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {

        if (validateCustomer(customer)) {
            return CustomerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de custo e preço de venda do produto são " +
                            "obrigatórios e devem ser maiores que 0 (zero)!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long customerId) {
        Optional<Customer> customer =
                Optional.ofNullable(CustomerRepository.findById(customerId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer não encontrado!")));

        CustomerRepository.delete(customer.get());
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        result.put("result", "Customer: " + customer.get().getPasswordCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer){
        return CustomerRepository.findById(idCustomer)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "customer não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {

        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O ID do Customer é obrigatório na atualização!");
        }

        if (validateCustomer(customer)) {
            if (findCustomerById(customer.getIdCustomer()) != null) {
                return CustomerRepository.saveAndFlush(customer);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de custo e preço de venda do produto são obrigatórios " +
                            "e devem ser maiores que 0 (zero)!");
        }
    }

    public Boolean validateCustomer (Customer customer) {
        if (customer.getCpfCustomer() != null &&
                customer.getCpfCustomer().compareTo(String.valueOf(0)) == 1 &&
                customer.getEmailCustomer() != null &&
                customer.getEmailCustomer().compareTo(String.valueOf(0)) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
