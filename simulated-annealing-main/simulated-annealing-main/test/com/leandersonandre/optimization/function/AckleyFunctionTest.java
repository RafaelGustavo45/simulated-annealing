package com.leandersonandre.optimization.function;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AckleyFunctionTest {


    @Test
    public void test_globalMinimun(){
        double[] x = new double[2];
        AckleyFunction ackleyFunction = new AckleyFunction();
        double result = ackleyFunction.evaluate(x);
        assertEquals(0.0, result, 0.001,"Global minimun must to be 0");
    }

    @Test
    public void test_evaluate(){
        double[] x = {1,1};
        AckleyFunction ackleyFunction = new AckleyFunction();
        double result = ackleyFunction.evaluate(x);
        assertEquals(3.626, result, 0.001,"f(1,1) must to be 3.626");
    }

    @Test
    public void test_validateDomain(){
        double[] x = new double[2];
        x[0] = -33;
        x[1] = 33;
        AckleyFunction ackleyFunction = new AckleyFunction();
        double result = ackleyFunction.evaluate(x);
        assertEquals(-32.768, x[0], 0.001,"Domain minimun value must to be -32.768");
        assertEquals(32.768, x[1], 0.001,"Domain maximun value must to be 32.768");
    }

}