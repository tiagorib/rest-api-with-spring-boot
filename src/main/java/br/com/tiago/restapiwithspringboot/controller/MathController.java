package br.com.tiago.restapiwithspringboot.controller;

import br.com.tiago.restapiwithspringboot.entity.Calculator;
import br.com.tiago.restapiwithspringboot.exception.UnsupportedMathOperationException;
import br.com.tiago.restapiwithspringboot.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator/v1")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {
        if (!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(numberOne, numberTwo, "+");
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {
        if (!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(numberOne, numberTwo, "-");
    }

    @GetMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
                              @PathVariable(value = "numberTwo") String numberTwo) {
        if (!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(numberOne, numberTwo, "*");
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String numberOne,
                                 @PathVariable(value = "numberTwo") String numberTwo) {
        if (!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(numberOne, numberTwo, "/");
    }

    @GetMapping("/average/{numberOne}/{numberTwo}")
    public Double average(@PathVariable(value = "numberOne") String numberOne,
                           @PathVariable(value = "numberTwo") String numberTwo) {
        if (!mathService.isNumeric(numberOne) || !mathService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(numberOne, numberTwo, "a");
    }

    @GetMapping("/sqrt/{number}")
    public Double average(@PathVariable(value = "number") String number) {
        if (!mathService.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Os valores digitados não são numéricos!");
        }
        return mathService.calculate(number, "0", "s");
    }

}