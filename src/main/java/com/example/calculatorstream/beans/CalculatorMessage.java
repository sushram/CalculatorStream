package com.example.calculatorstream.beans;

public class CalculatorMessage {

    private final String CALC_MESSAGE_FORMAT = "%d %s %d = %d";

    private int operand1;
    private int operand2;
    private String operator;
    private int result;

    public CalculatorMessage(int operand1, int operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;

        compute();
    }

    private void compute() {
        if("+".equals(operator)) {
           result = operand1 + operand2;
        } else if("-".equals(operator)) {
            result = operand1 - operand2;
        }else if("*".equals(operator)) {
            result = operand1 * operand2;
        }
    }

    public String toString() {
        return String.format(CALC_MESSAGE_FORMAT,operand1,operator,operand2,result);
    }
}
