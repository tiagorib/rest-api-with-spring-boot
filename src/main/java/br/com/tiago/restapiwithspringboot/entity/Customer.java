package br.com.tiago.restapiwithspringboot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "first_name_customer", nullable = false, length = 100)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 100)
    private String lastNameCustomer;

    @Column(name = "cpf_customer", nullable = false, unique = true, length = 11)
    private String cpfCustomer;

    @Column(name = "birthdate_customer", nullable = false)
    private LocalDate birthdateCustomer;

    @Column(name = "date_created_customer", nullable = false)
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_customer", nullable = false, unique = true, length = 200)
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false, length = 200)
    private String passwordCustomer;

}
