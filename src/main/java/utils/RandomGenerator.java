package utils;

import org.apache.commons.math3.random.MersenneTwister;

public class RandomGenerator {

    private final MersenneTwister rng;

    public RandomGenerator() {
        this.rng = new MersenneTwister();
    }

    public RandomGenerator(long seed) {
        this.rng = new MersenneTwister(seed);
    }

    public double nextUniform(){
        return rng.nextDouble();
    }

    public double nextGaussian(){
        return rng.nextGaussian();
    }
}