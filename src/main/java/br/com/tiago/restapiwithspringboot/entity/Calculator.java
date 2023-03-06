package br.com.tiago.restapiwithspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Calculator {

    private Double numberOne;
    private Double numberTwo;
    private String operation;


    public Double calc() throws Exception {

        if (this.getOperation().equalsIgnoreCase("+")) {
            return sum();
        } else {
            throw new Exception();
        }

    }
    private Double sum() {
        return this.getNumberOne() + this.getNumberTwo();
    }

}
