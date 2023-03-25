package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "first_name_customer", unique = true, nullable = false, length = 300)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 3000)
    private String lastNameCustomer;

    @Column(name = "cpf_customer", nullable = false, unique = true, length = 11)
    private String cpfCustomer;

    @Column(name = "birth_date_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")         // formata a data dessa forma
    private LocalDate birthDateCustomer;

    @Column(name = "date_created_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_customer", nullable = false)
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false)
    private String passwordCustomer;

    @PrePersist
    public void prePersist() {this.setDateCreatedCustomer(LocalDate.now());}


}
