package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
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
    //@NotBlank(massage = "O campo nome é obrigatório")
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 3000)
    private String lastNameCustomer;

    @CPF (message = "CPF inválido") // framework é o cu do gabriel dando bote
    @Column(name = "cpf_customer", nullable = false, unique = true, length = 11)
    //@CPF(massage = "O CPF é inválido")
    private String cpfCustomer;

    @Column(name = "birth_date_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")         // formata a data dessa forma
    private LocalDate birthDateCustomer;

    @Column(name = "date_created_customer", nullable = false, updatable = false) // Aqui eu não preciso validar nada
    @JsonFormat(pattern = "dd/MM/yyyy")                         // esse update falso não permite que esse dado seja alterado
    private LocalDate dateCreatedCustomer;

    @Column(name = "monthly_income_customer", nullable = false)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Email (message = "Email inválido") // é um recurso que a gente tem para fazer validação do email
    @Column(name = "email_customer", nullable = false)
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false)
    private String passwordCustomer;

    @PrePersist
    private void prePersist() {this.setDateCreatedCustomer(LocalDate.now());} // Aqui garante que a data seja automática
    // No ato de cadastrar ele roda a função do pre persist --> Ele pega a data atual e salva dentro do dateCreatedCustomer

}
