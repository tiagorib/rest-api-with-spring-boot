package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @CPF
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "birth_date")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthDate;
    @Column(name = "date_create", updatable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dateCreate;
    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;
    @Column(name = "status")
    private Boolean status;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;


    @PrePersist
    public void datinha(){
        setDateCreate(LocalDate.now());
    }
}
