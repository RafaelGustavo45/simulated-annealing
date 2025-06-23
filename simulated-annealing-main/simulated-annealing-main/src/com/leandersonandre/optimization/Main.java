package com.leandersonandre.optimization;

import com.leandersonandre.optimization.function.FunctionFactory;
import com.leandersonandre.optimization.sa.SimulatedAnnealing;
import com.leandersonandre.optimization.sa.operator.GaussianOperator;

public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing(
                FunctionFactory.getInstance().getFunction("BUKIN6"),
                new GaussianOperator(.5),
                0.96,
                200,
                100_000,
                2
        );
        sa.execute();
    }
}
