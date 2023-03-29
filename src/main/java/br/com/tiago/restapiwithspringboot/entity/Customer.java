package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;
    @Column(name = "first_name_customer",length = 50, nullable = false)
    private String firstNameCustomer;
    @Column(name = "last_name_customer",length = 250, nullable = false)
    private String lastNameCustomer;
    @CPF
    @Column (name = "cpf_customer", nullable = false,unique = true,length = 11)
    private String cpfCustomer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate_customer",nullable = false)
    private LocalDate birthdateCustomer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column (name = "date_created_customer", nullable = false, updatable = false)
    private LocalDate dateCreatedCustomer;
    @Column(name = "monthly_income_customer",precision = 10, scale = 2, nullable = false)
    private BigDecimal monthlyIncomeCustomer;
    @Column(name = "status_customer",nullable = false)
    private Boolean statusCustomer;
    @Column(name = "email_customer",length = 400, nullable = false)
   @Email
    private String emailCustomer;
    @Column(name = "password_customer",length = 150, nullable = false)
    private String passwordCustomer;

    @PrePersist
    public void prePersist() {setDateCreatedCustomer(LocalDate.now()); }


}

