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

    public List<Customer> getInfoCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        if (validateCustomer(customer)) {         // se a validação for verdadeira, cadastre!
            return customerRepository.saveAndFlush(customer); // saveandflush confirma o que ele fez, é mais rápido que o método só save
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O usuário é obrigatório e deve ser declarado!");
        }
    }

    public HashMap<String, Object> deleteCustomer(Long idCustomer) {
        Optional<Customer> customer = // criei uma variavel chamada customer. O optional é uma classe do java que procura os objetos
                Optional.ofNullable(customerRepository.findById(idCustomer).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //Optional.ofNullable ---> encontre o produto pelo id, se não for nulo ele pega o id, se for nulo, ele da a mensagem
                        "Usuário não encontrado!")));                           // o throw é o tratamento de exceções

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new  HashMap<String, Object> (); // HashMap --- > monto uma variável da forma que eu quiser
        result.put("result", "Usuário: " + customer.get().getFirstNameCustomer()+ customer.get().getLastNameCustomer() + " excluído com sucesso!");
        return result;
    }

    public Customer findCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public Customer updateCustomer(Customer customer) {
        if (customer.getIdCustomer() == null || customer.getIdCustomer() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do usuário é obrigatório na atualização!");
        }

        if (validateCustomer(customer)) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mensagem de erro");
        }
    }


        public Boolean validateCustomer (Customer customer) {

            if (customer.getCpfCustomer() != null &&
                    customer.getFirstNameCustomer() != null &&
                    customer.getLastNameCustomer() != null &&
                    customer.getEmailCustomer() != null &&
                    customer.getPasswordCustomer() != null &&
                    customer.getBirthDateCustomer() != null &&
                    customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) == 1) {
                return true;
            } else {
                return false;
            }
        }

    }
