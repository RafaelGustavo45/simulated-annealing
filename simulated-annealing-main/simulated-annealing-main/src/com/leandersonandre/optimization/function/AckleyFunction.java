package com.leandersonandre.optimization.function;

/**
 * Ackley Function
 * https://www.sfu.ca/~ssurjano/ackley.html
 */
public class AckleyFunction implements Function{
    private final static double a = 20;
    private final static double b = 0.2;
    private final static double c = 2*Math.PI;
    private final static double MIN_VALUE = -32.768;
    private final static double MAX_VALUE = 32.768;
    @Override
    public double evaluate(double[] x) {
        validateAndFixDomain(x);
        double sum = 0;
        double sum2 = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i]*x[i];
            sum2 += Math.cos(c*x[i]);
        }
        sum = -a * Math.exp(-b * Math.sqrt((1.0/x.length) * sum));
        sum2 = Math.exp((1.0/x.length) * sum2);
        return sum - sum2 + a + Math.exp(1);
    }

    @Override
    public void validateAndFixDomain(double[] x) {
        for(int i = 0; i < x.length; i++){
            if(x[i] < MIN_VALUE){
                x[i] = MIN_VALUE;
            }
            if(x[i] > MAX_VALUE){
                x[i] = MAX_VALUE;
            }
        }
    }

    @Override
    public void generateRandomSolution(double[] solution) {
        for(int i = 0; i < solution.length; i++){
            solution[i] = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
        }
    }
}
