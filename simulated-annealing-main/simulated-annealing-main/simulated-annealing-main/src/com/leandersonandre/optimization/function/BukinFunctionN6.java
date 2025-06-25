package com.leandersonandre.optimization.function;

/**
 * Bukin Function n.6
 * https://www.sfu.ca/~ssurjano/bukin6.html
 */
public class BukinFunctionN6 implements Function{
    private final static double MIN_VALUE[] = {-15,-3};
    private final static double MAX_VALUE[] = {-5,3};
    @Override
    public double evaluate(double[] x) {
        double a = Math.sqrt(Math.abs(x[1]-0.01 * (x[0]* x[0])));
        double b = 0.01 * Math.abs(x[0]+10.0);
        return 100.0 * a + b;
    }

    @Override
    public void validateAndFixDomain(double[] x) {
        for(int i = 0; i < x.length; i++){
            if(x[i] < MIN_VALUE[i]){
                x[i] = MIN_VALUE[i];
            }
            if(x[i] > MAX_VALUE[i]){
                x[i] = MAX_VALUE[i];
            }
        }
    }

    @Override
    public void generateRandomSolution(double[] solution) {
        for(int i = 0; i < 2; i++){
            solution[i] = MIN_VALUE[i] + (MAX_VALUE[i] - MIN_VALUE[i]) * Math.random();
        }
    }
}
