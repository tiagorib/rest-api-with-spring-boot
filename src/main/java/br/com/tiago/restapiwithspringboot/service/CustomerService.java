package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.math.BigDecimal;
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
        if(validateCustomer(customer)) {
            return customerRepository.saveAndFlush(customer);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Existem campos em branco e/ou vazios");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long idCustomer) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(idCustomer). //antes de deletar vou verificar a existencia do id na lista
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        result.put("result", "Usuário: " + customer.get().getFirstNameCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer){
        return customerRepository.findById(idCustomer)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {
        if(customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do usuário é obrigatório na atualização!");
        }
        if(validateCustomer(customer)) {
            if(findCustomerById(customer.getIdCustomer()) != null){
                return customerRepository.saveAndFlush(customer);
            }else{
                return null;
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preenchimento de todos os campos é obrigatório!");
        }
    }

    public Boolean validateCustomer (Customer customer) {
        if(customer.getFirstNameCustomer() != null &&
                customer.getLastNameCustomer() != null &&
                customer.getCpfCustomer() != null &&
                customer.getEmailCustomer() != null &&
                customer.getPasswordCustomer() != null &&
                customer.getBirthdateCustomer() != null &&
                customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) == 1 &&
                customer.getStatusCustomer() != null)
        {
            return true;
        }else{
            return false;
        }
    }
}