package com.leandersonandre.optimization.function;

public interface Function {

    double evaluate(double[] x);
    void validateAndFixDomain(double[] x);
    void generateRandomSolution(double[] x);

}
