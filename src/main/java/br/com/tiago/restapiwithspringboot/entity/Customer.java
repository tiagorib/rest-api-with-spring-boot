package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.validator.constraints.br.CPF;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer")
    private Long idCustomer;

    @Column(name = "firstNameCustomer", unique = true, nullable = false, length = 50)
    private String firstNameCustomer;

    @Column(name = "lastNameCustomer", unique = true, nullable = false, length = 50)
    private String lastNameCustomer;

    @CPF
    @Column(name = "cpfCustomer", unique = true, nullable = false, length = 14)
    private String cpfCustomer;

    @Email
    @Column(name = "emailCustomer", unique = true, nullable = false, length = 100)
    private String emailCustomer;

    @Column(name = "passwordCustomer", unique = true, nullable = false, length = 50)
    private String passwordCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdateCustomer", nullable = false)
    private LocalDate birthdateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dateCreatedCustomer", nullable = false, updatable = false)
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthlyIncomeCustomer", unique = true, nullable = false, length = 10)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "StatusCustomer", unique = true, nullable = false, length = 1)
    private String statusCustomer;

    @PrePersist
    public void prePersist(){
        setDateCreatedCustomer(LocalDate.now());
    }
}