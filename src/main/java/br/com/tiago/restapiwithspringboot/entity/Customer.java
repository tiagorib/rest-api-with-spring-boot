package br.com.tiago.restapiwithspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCustomer;
    @Column(name = "first_name")
    private String firstNameCustomer;
    @Column(name = "last_name")
    private String lastNameCustomer;
    @Column(name = "cpf")
    private String cpfCustomer;
    @Column(name = "birthday")
    private LocalDate birthdateCustomer;
    @Column(name = "created")
    private LocalDate dateCreatedCustomer;
    @Column(name = "monthly_income")
    private BigDecimal monthlyIncomeCustomer;
    @Column(name = "status")
    private Boolean statusCustomer;
    @Column(name = "email")
    private String emailCustomer;
    @Column(name = "password")
    private String passwordCustomer;


    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstNameCustomer() {
        return firstNameCustomer;
    }

    public void setFirstNameCustomer(String firstNameCustomer) {
        this.firstNameCustomer = firstNameCustomer;
    }

    public String getLastNameCustomer() {
        return lastNameCustomer;
    }

    public void setLastNameCustomer(String lastNameCustomer) {
        this.lastNameCustomer = lastNameCustomer;
    }

    public String getCpfCustomer() {
        return cpfCustomer;
    }

    public void setCpfCustomer(String cpfCustomer) {
        this.cpfCustomer = cpfCustomer;
    }

    public LocalDate getBirthdateCustomer() {
        return birthdateCustomer;
    }

    public void setBirthdateCustomer(LocalDate birthdateCustomer) {
        this.birthdateCustomer = birthdateCustomer;
    }

    public LocalDate getDateCreatedCustomer() {
        return dateCreatedCustomer;
    }

    public void setDateCreatedCustomer(LocalDate dateCreatedCustomer) {
        this.dateCreatedCustomer = dateCreatedCustomer;
    }

    public BigDecimal getMonthlyIncomeCustomer() {
        return monthlyIncomeCustomer;
    }

    public void setMonthlyIncomeCustomer(BigDecimal monthlyIncomeCustomer) {
        this.monthlyIncomeCustomer = monthlyIncomeCustomer;
    }

    public Boolean getStatusCustomer() {
        return statusCustomer;
    }

    public void setStatusCustomer(Boolean statusCustomer) {
        this.statusCustomer = statusCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getPasswordCustomer() {
        return passwordCustomer;
    }

    public void setPasswordCustomer(String passwordCustomer) {
        this.passwordCustomer = passwordCustomer;
    }
}