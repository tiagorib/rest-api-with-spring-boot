package br.com.tiago.restapiwithspringboot.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor //construtor vazio
@AllArgsConstructor
@Data //gets e setters

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_customer")
    private Long idCustomer;

    @Column(name="first_name_customer", nullable = false, length = 20)
    private String firstNameCustomer;

    @Column(name="last_name_customer", nullable = false, length = 40)
    private String lastNameCustomer;

    @CPF
    @Column(name="cpf_customer", unique = true, nullable = false, length = 11)
    private String cpfCustomer;

    @Column(name="birth_date_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDateCustomer;

    @Column(name="date_create_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreateCustomer;

    @Column(name="monthly_income_customer", nullable = false, length = 10)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name="status_customer", nullable = false)
    private String statusCustomer;

    @Email
    @Column(name="email_customer", nullable = false)
    private String emailCustomer;

    @Column(name="password_customer", nullable = false)
    private String passwordCustomer;

    @PrePersist
    public void prePersist()
    {setDateCreateCustomer(LocalDate.now());}


}