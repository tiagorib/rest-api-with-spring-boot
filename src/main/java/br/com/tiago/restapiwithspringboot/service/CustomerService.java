package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Customer;
import br.com.tiago.restapiwithspringboot.repository.CustomerRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.annotation.*;
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
    @Documented
    @Constraint(validatedBy = CpfValidator.class)
    @Target( { ElementType.METHOD, ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Cpf {

        String message() default "CPF Inválido";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

    }
    public class CpfValidator implements ConstraintValidator<Cpf, String> {

        private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        @Override
        public boolean isValid(String cpf, ConstraintValidatorContext context) {

            String cpfSomenteDigitos = cpf.replaceAll("\\D", "");

            if ((cpfSomenteDigitos == null) || (cpfSomenteDigitos.length() != 11) || cpfSomenteDigitos.equals("00000000000")
                    || cpfSomenteDigitos.equals("11111111111") || cpfSomenteDigitos.equals("22222222222")
                    || cpfSomenteDigitos.equals("33333333333") || cpfSomenteDigitos.equals("44444444444")
                    || cpfSomenteDigitos.equals("55555555555") || cpfSomenteDigitos.equals("66666666666")
                    || cpfSomenteDigitos.equals("77777777777") || cpfSomenteDigitos.equals("88888888888")
                    || cpfSomenteDigitos.equals("99999999999")) {
                return false;
            }

            Integer digito1 = calcularDigito(cpfSomenteDigitos.substring(0, 9), PESO_CPF);
            Integer digito2 = calcularDigito(cpfSomenteDigitos.substring(0, 9) + digito1, PESO_CPF);

            return cpfSomenteDigitos.equals(cpfSomenteDigitos.substring(0, 9) + digito1.toString() + digito2.toString());
        }

        private int calcularDigito(String str, int[] peso) {
            int soma = 0;
            for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
                digito = Integer.parseInt(str.substring(indice, indice + 1));
                soma += digito * peso[peso.length - str.length() + indice];
            }
            soma = 11 - soma % 11;
            return soma > 9 ? 0 : soma;
        }

    }
}
