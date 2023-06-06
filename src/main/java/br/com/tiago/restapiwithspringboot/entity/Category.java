package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

     @Column(name = "name_category", nullable = false, length = 300,unique = true)
    @NotNull(message = "O campo nome é obrigatório!")
    @Length(min=2,max = 300,message = "O nome deve ter ao menos dois caracteres!")
    private String nameCategory;

    @Column(name = "description_category", nullable = false)
    @Length(min=3,max = 500,message = "A descrição de categoria deve ser informada!")
    private String descriptionCategory;

}
