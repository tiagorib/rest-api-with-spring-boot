package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
>>>>>>> simple-crud
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
<<<<<<< HEAD
            customer.setDateCreatedCustomer(LocalDate.now());
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O campo cpfCustomer é obrigatório, deve conter 11 números " +
                            "e não pode conter letras ou caracteres especiais!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long customerId) {
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findById(customerId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado!")));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "Cliente: " + customer.get().getFirstNameCustomer() + " " +
                customer.get().getLastNameCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {

        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O ID do cliente é obrigatório na atualização!");
        }

        if (validateCustomer(customer)) {
            if (findCustomerById(customer.getIdCustomer()) != null) {
=======
            encryptPassword(customer);
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de venda do produto é " +
                            "obrigatório e deve ser maior que 0 (zero)!");
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
>>>>>>> simple-crud
                return customerRepository.saveAndFlush(customer);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
<<<<<<< HEAD
                    "O campo cpfCustomer é obrigatório, deve conter 11 números " +
                            "e não pode conter letras ou caracteres especiais!");
=======
                    "A renda salarial deve ser maior ou igual 0!");
>>>>>>> simple-crud
        }
    }

    public Boolean validateCustomer(Customer customer) {
<<<<<<< HEAD
        if (customer.getCpfCustomer() != null &&
                customer.getCpfCustomer().matches("[0-9]{11}")) {
=======
        if (customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) >= 0 &&
                customer.getPasswordCustomer() != null &&
                !customer.getPasswordCustomer().equals("")) {
>>>>>>> simple-crud
            return true;
        } else {
            return false;
        }
    }
<<<<<<< HEAD
        public void encryptPassword(Customer customer){
        if (!customerRepository.findById(customer.getIdCustomer()).get().getPasswordCustomer().equals(customer.getPasswordCustomer())){
            BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
            String encryptedPassword = encrypt.encode(customer.getPasswordCustomer());
            customer.setPasswordCustomer(encryptedPassword);
        }
            }
                }



=======

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
>>>>>>> simple-crud
