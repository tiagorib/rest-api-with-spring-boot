package br.com.tiago.restapiwithspringboot.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "first_name_customer", nullable = false, length = 300)
    @NotBlank (message = "O campo nome é obrigatório!")
    @Length(min = 2, max = 300, message = "O nome deve ter ao menos dois caracteres!")
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 300)
    private String lastNameCustomer;

    @Column(name = "cpf_customer", unique = true, nullable = false, length = 11)
    @CPF(message = "O CPF é inválido!")
    private String cpfCustomer;

    @Column(name = "birthdate_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdateCustomer;

    @Column(name = "date_created_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_customer", unique = true, nullable = false, length = 300)
    @Email (message = "O Email informado é inválido!")
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false, length = 3000)
    private String passwordCustomer;

    @PrePersist
    private void prePersist() {
        this.setDateCreatedCustomer(LocalDate.now());
    }

}
