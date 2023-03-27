package br.com.tiago.restapiwithspringboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
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

    @Column(name = "first_name_customer", nullable = false, length = 30)
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 200)
    private String lastNameCustomer;

    @CPF
    @Column(name = "cpf_customer", nullable = false, length = 11, unique = true)
    private String cpfCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate_customer", nullable = false)
    private LocalDate birthdateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_register_customer", nullable = false, updatable = false)
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_customer", nullable = false, length = 200)
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false, length = 200)
    private String passwordCustomer;

    @PrePersist
    public void prePersist(){setDateCreatedCustomer(LocalDate.now());}
}



