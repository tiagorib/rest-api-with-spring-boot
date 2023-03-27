package br.com.tiago.restapiwithspringboot.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "First Name is required!")
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 200)
    @NotNull(message = "Last Name is required!")
    private String lastNameCustomer;

    @Column(name = "cpf_customer", nullable = false, length = 11, unique = true)
    @CPF(message = "O CPF informado é inválido")
    @NotNull(message = "CPF is required!")
    private String cpfCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate_customer", nullable = false)
    @NotNull(message = "Birthdate is required!")
    private LocalDate birthdateCustomer;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_register_customer", nullable = false, updatable = false)
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Monthly Income is required!")
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    @NotNull(message = "Status is required!")
    private Boolean statusCustomer;

    @Column(name = "email_customer", nullable = false, length = 200)
    @Email(message = "O e-mail informado é inválido")
    @NotNull(message = "E-mail is required!")
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false, length = 200)
    @NotNull(message = "Password is required!")
    private String passwordCustomer;

    @PrePersist
    public void prePersist(){setDateCreatedCustomer(LocalDate.now());}
}



