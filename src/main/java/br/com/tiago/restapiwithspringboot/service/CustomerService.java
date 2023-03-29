package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer save(Customer product) {
        if (validateCustomer(product)) {
            Customer result = repository.save(product);
            if (result != null) {
                return result;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de custo e o preço de venda são obrigatórios e devem ser maior que 0!");
        }
        return null;
    }

    public List<Customer> list() {
        List<Customer> result = repository.findAll();
        return result;
    }

    public Customer update(Customer product) {
        if (validateCustomer(product)) {
            Customer result = repository.saveAndFlush(product);
            if (result != null) {
                return result;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O preço de custo e o preço de venda são obrigatórios e devem ser maior que 0!");
        }
        return null;
    }

    public Optional<Customer> findById(Long id) {
        if (id != null) {
            Optional<Customer> result = repository.findById(id);
            if (result.isPresent()) {
                return result;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id está nulo");
        }
        return null;
    }

    public String delete(Long id) {
        if(id != null) {
            repository.deleteById(id);
            return "Produto deletado com sucesso";
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id está nulo");
        }
    }

    public Boolean validateCustomer(Customer product) {
        if (product.getCpf() != null) {
            return true;
        } else {
            return false;
        }
    }

}
