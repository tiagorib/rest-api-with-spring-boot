package br.com.tiago.restapiwithspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private long idCustomer;
    @Column(name = "firstnamecustomer", nullable = false, length = 50)
    private String firstNameCustomer;
    @Column(name = "lastnamecustomer", nullable = false, length = 100)
    private String LastNameCustomer;
    @Column(name = "cpfcustomer", nullable = false, unique = true, length = 14)
    private String cpfCustomer;
    @Column(name = "emailcustomer", nullable = false, unique = true, length = 80)
    private String emailCustomer;
    @Column(name = "birthdatecustomer", nullable = false)
    private LocalDate birthDateCustomer;
    @Column(name = "datecreatedcustomer", nullable = false)
    private LocalDate dateCreatedCustomer;
    @Column(name = "monthlyincomecustomer", nullable = false)
    private BigDecimal  monthlyIncomeCustomer;
    @Column(name = "passwordcustomer", nullable = false, length = 30)
    private String passwordCustomer;
    @Column(name = "statuscustomer", nullable = false)
    private Boolean statusCustomer;

}