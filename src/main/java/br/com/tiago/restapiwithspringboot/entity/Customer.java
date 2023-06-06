package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.security.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @NotBlank(message = "O campo nome é obrigatório!")
    @Column(name = "first_name_customer", nullable = false, length = 100)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 200)
    private String lastNameCustomer;


    @CPF(message = "CPF INVÁLIDO!")
    @Column(name = "cpf_customer", unique = true,  nullable = false, length = 11) //ver caracteristicas pra atributo
    private String cpfCustomer;


    @Email(message = "EMAIL INVÁLIDO")
    @Column(name = "email_customer", unique = true, nullable = false, length = 100)
    private String emailCustomer;


    @Column(name = "birthdate_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdateCustomer;


    @Column(name = "date_create_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedCustomer;


    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;


    @Column(name = "password_customer", nullable = false, length = 30000)
    private String passwordCustomer;


    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;


    @PrePersist
    public void prePersist() {
        this.setDateCreatedCustomer(LocalDate.now()); //data automatica
    }
}
