package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer {

    private Long idCustomer;
    private String firstNameCustomer;
    private String lastNameCustomer;
    private String cpfCustomer;
    private LocalDate birthdateCustomer;
    private LocalDate dateCreatedCustomer;
    private BigDecimal monthlyIncomeCustomer;
    private Boolean statusCustomer;
    private String emailCustomer;
    private String passwordCustomer;

}