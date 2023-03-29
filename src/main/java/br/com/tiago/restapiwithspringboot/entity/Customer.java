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
    @Column(name = "id_Customer")
    private Long idCustomer;

    @Column(name = "firstname_Customer", nullable = false, length = 300)
    private String firstNameCustomer;

    @Column(name = "lastname_Customer", nullable = false, length = 300)
    private String lastNameCustomer;

    @Column(name = "cpf_Customer", unique = true, nullable = false, length = 11)
    private String cpfCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthDate_Customer", nullable = false)
    private LocalDate birthDateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dateCreated_Customer", nullable = false, updatable = false )
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthlyIncome_Customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_Customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_Customer", nullable = false, length = 300)
    private String emailCustomer;

    @Column(name = "password_Customer")
    private String passwordCustomer;

    @PrePersist
    public void prePersist(){
        setDateCreatedCustomer(LocalDate.now());
    }
}

