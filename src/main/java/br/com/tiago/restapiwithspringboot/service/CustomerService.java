package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.entity.Product;
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

    public List<Customer> getInfoCustomer() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        if (validateCustomer(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
<<<<<<< HEAD
                    "Favor digitar todos os campos!");
=======
                    "O preço de custo e preço de venda do produto são " +
                            "obrigatórios e devem ser maiores que 0 (zero)!");
>>>>>>> parent of 9a135ee (Crud ok)
        }
    }

    public HashMap<String, Object> deleteCustomer(Long customerId) {
        Optional<Customer> customer =
                Optional.ofNullable(customerRepository.findById(customerId).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer não encontrado!")));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new  HashMap<String, Object> ();
        result.put("result", "Customer: " + customer.get().getFirstNameCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer){
        return customerRepository.findById(idCustomer)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer não encontrado!"));
    }
    public List<Customer> findAllCustomersByStatus(Boolean statusCustomer) {
        return customerRepository.findCustomersByStatus(statusCustomer);
    }


    public Customer updateProduct(Customer customer) {

        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O ID do customer é obrigatório na atualização!");
        }

        if (validateCustomer(customer)) {
            if (findCustomerById(customer.getIdCustomer()) != null) {
                return customerRepository.saveAndFlush(customer);
            } else {
                return null;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
<<<<<<< HEAD
            "Favor digitar todos os campos");
=======
            "O preço de custo e preço de venda do produto são obrigatórios " +
                "e devem ser maiores que 0 (zero)!");
>>>>>>> parent of 9a135ee (Crud ok)
        }
    }

    public Boolean validateCustomer (Customer customer) {
<<<<<<< HEAD
        if (customer.getFirstNameCustomer() != null && customer.getLastNameCustomer() != null &&
                customer.getCpfCustomer() != null && customer.getBirthdateCustomer() != null &&
                customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) == 1 &&
                customer.getStatusCustomer() != null && customer.getEmailCustomer() != null &&
                customer.getPasswordCustomer() != null) {
=======
        if (customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) == 1) {
>>>>>>> parent of 9a135ee (Crud ok)
            return true;
        } else {
            return false;
        }
    }

}