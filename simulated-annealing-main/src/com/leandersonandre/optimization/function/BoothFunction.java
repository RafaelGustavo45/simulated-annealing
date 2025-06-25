package com.leandersonandre.optimization.function;

public class BoothFunction implements Function{

    private final static double MIN_VALUE[] = {-10,-10};
    private final static double MAX_VALUE[] = {10,10};

    @Override
    public double evaluate(double[] x) {
        double term1 = Math.pow((x[0] + 2*x[1] - 7.0), 2);
        double term2 = Math.pow((2*x[0] + x[1] - 5.0), 2);

        return term1+term2;
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
