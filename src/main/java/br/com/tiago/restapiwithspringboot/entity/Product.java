package br.com.tiago.restapiwithspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

}