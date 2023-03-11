package br.com.tiago.restapiwithspringboot.service;

import br.com.tiago.restapiwithspringboot.entity.Calculator;
import br.com.tiago.restapiwithspringboot.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double calculate(String numberOne, String numberTwo, String operation) throws UnsupportedMathOperationException {
        Calculator calculator = new Calculator();
        calculator.setNumberOne(convertStringToDouble(numberOne));
        calculator.setNumberTwo(convertStringToDouble(numberTwo));
        calculator.setOperation(operation);
        return calculator.calc();
    }

    public Boolean isNumeric(String numberStr) {
        if (numberStr == null || numberStr.equals("")) {
            return false;
        } else {
            String number = numberStr.replace(",", "." );
            return number.matches("[-+]?[0-9]*\\.?[0-9]+");
        }
    }

    public Double convertStringToDouble(String numberStr) {
        if (numberStr == null || numberStr.equals("")) {
            return 0D;
        } else {
            String number = numberStr.replace(",", "." );
            return Double.parseDouble(number);
        }
    }

}