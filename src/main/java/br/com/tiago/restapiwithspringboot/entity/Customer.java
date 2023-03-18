package br.com.tiago.restapiwithspringboot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer {

    private Long idCustomer;
    private String firstNameCustomer;
    private String lastNameCustomer;
    private String cpfCustomer;
    private LocalDate birthDateCustomer;
    private LocalDate dateCreatedCustomer;
    private BigDecimal monthlyIncomeCustomer;
    private Boolean statusCustomer;
    private String emailCustomer;
    private String passwordCustomer;

}