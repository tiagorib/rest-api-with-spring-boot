package br.com.tiago.restapiwithspringboot.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConverterData {

    public BigDecimal convertingStringToBigDecimal(String value) {
        if (value.equals("") || value == null) {
            return null;
        } else {
            value = value.replace(".", "").replace(",", ".");
            return new BigDecimal(value);
        }
    }
}