package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", updatable = false)
    private Long idCustomer;

    @Column(name = "first_name_customer", nullable = false)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false)
    private String lastNameCustomer;

    @CPF
    @Column(name = "cpf_customer", nullable = false, unique = true, length = 11)
    private String cpfCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "birthday_customer", nullable = false)
    private LocalDate birthDateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_created_customer", nullable = false, updatable = false)
    private LocalDateTime dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Email
    @Column(name = "email_customer", nullable = false, unique = true)
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false)
    private String passwordCustomer;

    @PrePersist
    public void prePersist(){
        setDateCreatedCustomer(LocalDateTime.now());
        setStatusCustomer(true);
    }

}