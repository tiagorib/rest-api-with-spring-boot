package br.com.tiago.restapiwithspringboot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String nameProduct;
    private String descriptionProduct;
    private String costPriceProduct;
    private String amountProduct;
    private String dateCreatedProduct;
    private Long idCategory;

}
