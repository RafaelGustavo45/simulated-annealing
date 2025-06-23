package com.leandersonandre.optimization.function;

public class GramacyLeeFunction implements Function{

    private final static double MIN_VALUE = 0.5;
    private final static double MAX_VALUE = 2.5;
    @Override
    public double evaluate(double x){
        validateAndFixDomain(x);
        double s=Math.sin(10*Math.PI * x);
        double abaixo = 2.0*x;
        double div=s/abaixo;
        double quadri = Math.pow(x-1, 4);
        return div+quadri;
    }

    @Override
    public void validateAndFixDomain(double x) {
            if(x < MIN_VALUE){
                x = MIN_VALUE;
                //return x;

            if(x> MAX_VALUE)
                x= MAX_VALUE;
                //return x;

        }
            //return x;
    }
    @Override
    public void generateRandomSolution(double[] solution) {
        for(int i = 0; i < solution.length; i++){
            solution[i] = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
        }
    }


}
