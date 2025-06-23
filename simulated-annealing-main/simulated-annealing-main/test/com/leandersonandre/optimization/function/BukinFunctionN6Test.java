package com.leandersonandre.optimization.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BukinFunctionN6Test {

    @Test
    public void test_globalMinimun(){
        double[] x = new double[2];
        x[0] = -10;
        x[1] = 1;
        Function bukinFunctionN6 = new BukinFunctionN6();
        double result = bukinFunctionN6.evaluate(x);
        assertEquals(0.0, result, 0.001,"Global minimun must to be 0");
    }

    @Test
    public void test_evaluate(){
        double[] x = new double[2];
        x[0] = 1;
        x[1] = 1;
        Function bukinFunctionN6 = new BukinFunctionN6();
        double result = bukinFunctionN6.evaluate(x);
        assertEquals(99.609, result, 0.001,"x1=1 e x2= 1 must to be 99.609");
    }

    @Test
    public void test_validateDomain(){
        double[] x = new double[2];
        x[0] = -33;
        x[1] = -4;
        Function bukinFunctionN6 = new BukinFunctionN6();
        bukinFunctionN6.validateAndFixDomain(x);
        assertEquals(-15, x[0], 0.001,"x[0] minimun value must to be -15");
        assertEquals(-3, x[1], 0.001,"x[1]  minimun value must to be -3");
        x[0] = 0;
        x[1] = 4;
        bukinFunctionN6.validateAndFixDomain(x);
        assertEquals(-5, x[0], 0.001,"x[0] maximun value must to be -5");
        assertEquals(3, x[1], 0.001,"x[1] maximun value must to be 3");

    }

}