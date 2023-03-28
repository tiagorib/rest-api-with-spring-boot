package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;
import java.time.LocalDate;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builde:r


public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;


    @Column(name = "first_name_customer", nullable = false, length = 100)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 200)
    private String lastNameCustomer;

    @CPF(message = "CPF INVÁLIDO!")
    @Column(name = "cpf_customer", unique = true,  nullable = false, length = 11) //ver caracteristicas pra atributo
    private String cpfCustomer;

    @Email(message = "EMAIL INVÁLIDO")
    @Column(name = "email_customer", nullable = false, length = 100)
    private String emailCustomer;

    @Column(name = "birthdate_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdateCustomer;

    @Column(name = "date_create_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "password_customer", nullable = false, length = 10)
    private String passwordCustomer;

    @Column(name = "status_customer")
    private Boolean statusCustomer;


    @PrePersist
    public void prePersist() {
        setDateCreatedCustomer(LocalDate.now());
    }
}