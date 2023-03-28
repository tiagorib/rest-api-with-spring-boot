package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Boolean validateCustomer(Customer customer) {
        if (customer.getCpfCustomer() != null &&
                customer.getEmailCustomer() != null &&
                customer.getCpfCustomer() != null &&
                customer.getPasswordCustomer() != null &&
                customer.getBirthdateCustomer() != null &&
                customer.getFirstNameCustomer() != null &&
                customer.getLastNameCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Customer saveCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todos os campos são de preenchimento obrigatório!");
        }
    }

    public List<Customer> getInfoCustomers() {
        return customerRepository.findAll();
    }

    public HashMap<String, Object> deleteCustomer(Long customerId) {
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findById(customerId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado!")));
        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Cliente: " + customer.get().getFirstNameCustomer() + "Excluído com sucesso!");
        return result;
    }

    public Customer findCustomerByid(Long idCustomer) {
        return customerRepository.findById(idCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {
        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O Id do cliente é obrigatório na atualização!");
        }
        if (validateCustomer(customer)) {
            if (findCustomerByid(customer.getIdCustomer()) != null) {
                return customerRepository.saveAndFlush(customer);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todos os campos são de preenchimento obrigatório!");
        }
    }
}
