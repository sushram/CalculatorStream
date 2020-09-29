package com.example.calculatorstream;

import com.example.calculatorstream.beans.CalculatorMessage;
import com.example.calculatorstream.configuration.MessageDispather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to proccess calculate request
 */
@RestController
public class CalculatorController {

    @Autowired
    MessageDispather dispather;

    @PostMapping("/calculate")
    public void postCalculator(@RequestParam String operand1,
                               @RequestParam String operand2,
                               @RequestParam String operator) {
        dispather.broadcastMessage(new CalculatorMessage(Integer.valueOf(operand1),Integer.valueOf(operand2),operator));
    }
}
