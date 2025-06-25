package com.leandersonandre.optimization.sa.operator;

import java.util.Random;

public class GaussianOperator implements NeighborOperator {
    private Random random = new Random();
    private double mutationRate = 0.4;

    public GaussianOperator(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public void generateNeighbor(double[] x) {
        for (int i = 0; i < x.length; i++) {
            if(Math.random() < mutationRate) {
                x[i] += random.nextGaussian() ;
            }
        }
    }
}
