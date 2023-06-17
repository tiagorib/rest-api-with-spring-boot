package br.com.tiago.restapiwithspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "name_category", nullable = false, length = 300, unique = true)
    @NotBlank(message = "O campo nome é obrigatório!")
    @Length(min = 2, max = 300, message = "O nome deve ter ao menos dois caracteres!")
    private String nameCategory;

    @Column(name = "description_category", length = 1000)
    private String descriptionCategory;

}