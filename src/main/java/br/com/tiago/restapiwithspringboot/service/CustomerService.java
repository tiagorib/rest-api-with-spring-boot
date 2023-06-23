package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getInfoCustomers() {

        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            encryptPassword(customer);
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algum campo obrigatório está faltando ou inválido!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long idCustomer) {
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findById(idCustomer).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado!")));


        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Cliente: " + customer.get().getFirstNameCustomer() + " " + customer.get().getLastNameCustomer() +  " excluído com sucesso!");
        return result;
    }

    public Optional<Customer> findCustomerById(Long idCustomer) {
        return Optional.ofNullable(customerRepository.findById(idCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado!")));
    }

    public Customer updateCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            if (findCustomerById(customer.getIdCustomer()) != null) {
                encryptPassword(customer);
                return customerRepository.saveAndFlush(customer);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A renda salarial deve ser maior ou igual 0!");
        }
    }

    public Boolean validateCustomer(Customer customer) {
        if (customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) >= 0 &&
                customer.getPasswordCustomer() != null &&
                !customer.getPasswordCustomer().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void encryptPassword(Customer customer){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String encryptedPassword = null;
        if (customer.getIdCustomer() == null) {
            encryptedPassword = encrypt.encode(customer.getPasswordCustomer());
            customer.setPasswordCustomer(encryptedPassword);
        } else {
            if (!customerRepository.findById(customer.getIdCustomer()).get().getPasswordCustomer()
                    .equals(customer.getPasswordCustomer())) {
                encryptedPassword = encrypt.encode(customer.getPasswordCustomer());
                customer.setPasswordCustomer(encryptedPassword);
            }
        }

    }
}

