package models;

import org.apache.commons.math3.random.MersenneTwister;

public class MonteCarlo {

    private final MersenneTwister rng;

    public MonteCarlo() {
        this.rng = new MersenneTwister();
    }

    public double brownianMotion(double s, double r, double sigma, double t) {
        double z = rng.nextGaussian();
        return s * Math.exp((r - 0.5 * Math.pow(sigma, 2)) * t + sigma * Math.sqrt(t) * z);
    }

    public double callPayoff(double sT, double k) {
        return Math.max(sT - k, 0.0);
    }

    public double putPayoff(double sT, double k) {
     return Math.max(sT - k, 0.0);
    }

    double priceCallMc(double s, double k, double r, double sigma, double t, int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            double sT = brownianMotion(s, r, sigma, t);
           sum += callPayoff(sT, k);
        }
        return Math.exp(-r*t) * sum / n;
    }

    double pricePutMc(double s, double k, double r, double sigma, double t, int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            double sT = brownianMotion(s, r, sigma, t);
            sum += putPayoff(sT, k);
        }
        return Math.exp(-r*t) * sum / n;
    }

}