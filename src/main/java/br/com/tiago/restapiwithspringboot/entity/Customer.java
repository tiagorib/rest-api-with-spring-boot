package br.com.tiago.restapiwithspringboot.entity;

import br.com.tiago.restapiwithspringboot.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;
    @Column(name = "first_name_customer", nullable = false, length = 50)
    private String firstNameCustomer;
    @Column(name = "last_name_customer", nullable = false, length = 50)
    private String lastNameCustomer;
    @CustomerService.Cpf
    @Column(name = "cpf_customer", nullable = false, length = 11)
    private String cpfCustomer;
    @Column(name = "birth_date_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdateCustomer;
    @Column(name = "date_created_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedCustomer;
    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;
    private Boolean statusCustomer;
    @Column(name = "email_customer", nullable = false, length = 100)
    private String emailCustomer;
    @Column(name = "password_customer", nullable = false, length = 16)
    private String passwordCustomer;

    @PrePersist
    public void prePersist(){ setDateCreatedCustomer(LocalDate.now());}

}