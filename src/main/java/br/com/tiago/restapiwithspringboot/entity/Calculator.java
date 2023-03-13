package br.com.tiago.restapiwithspringboot.entity;

import br.com.tiago.restapiwithspringboot.exception.UnsupportedMathOperationException;
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


    public Double calc() {

        if (this.getOperation().equalsIgnoreCase("+")) {
            return sum();
        } else if (this.getOperation().equalsIgnoreCase("-")) {
            return subtraction();
        } else if (this.getOperation().equalsIgnoreCase("*")) {
            return multiplication();
        } else if (this.getOperation().equalsIgnoreCase("/")) {
            return division();
        } else if (this.getOperation().equalsIgnoreCase("a")) {
            return average();
        } else if (this.getOperation().equalsIgnoreCase("s")) {
            return squareRoot();
        } else {
            throw new UnsupportedMathOperationException("A operação desejada não é válida!");
        }

    }
    private Double sum() {
        return this.getNumberOne() + this.getNumberTwo();
    }

    private Double subtraction() {
        return this.getNumberOne() - this.getNumberTwo();
    }

    private Double multiplication() {
        return this.getNumberOne() * this.getNumberTwo();
    }

    private Double division() {
        return this.getNumberOne() / this.getNumberTwo();
    }

    private Double average() {
        return (this.getNumberOne() + this.getNumberTwo()) / 2;
    }

    private Double squareRoot() {
        return Math.sqrt(this.getNumberOne());
    }

}
