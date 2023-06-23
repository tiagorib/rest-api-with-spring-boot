package br.com.tiago.restapiwithspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.ast.tree.from.MappedByTableGroup;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_product", unique = true, nullable = false, length = 300)
    private String nameProduct;

    @Column(name = "description_product", nullable = false, length = 3000)
    private String descriptionProduct;

    @Column(name = "cost_price_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPriceProduct;

    @Column(name = "amount_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountProduct;
    @Column(name = "date_created_product", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreatedProduct;

    @ManyToOne
    @JoinColumn(name="id_category", nullable=false)
    @JsonBackReference
    private Category category;

    @PrePersist
    private void prePersist() {
        this.setDateCreatedProduct(LocalDate.now());
    }
}