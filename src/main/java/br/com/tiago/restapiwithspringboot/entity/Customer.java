package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "firstname_Customer", nullable = false)
    private String firstNameCustomer;
    @Column(name = "lastname_Customer", nullable = false)
    private String lastNameCustomer;
    @Column(name = "cpf_customer", nullable = false, unique = true, length = 11)
    private String cpfCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "birthdate_customer", nullable = false)
    private LocalDate birthdateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "date_created_customer", nullable = false, updatable = false)
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false)
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
        setDateCreatedCustomer(LocalDate.now());
        setStatusCustomer(true);
    }

}