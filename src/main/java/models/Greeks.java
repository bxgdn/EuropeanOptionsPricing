package models;

import utils.NormalDistributionUtil;

public class Greeks {

    public double dOne(double s, double k, double r, double sigma, double t) {
        return (Math.log(s / k) + (r + 0.5 * sigma * sigma) * t) / (sigma * Math.sqrt(t));
    }

    public double dTwo(double s, double k, double r, double sigma, double t) {
        return dOne(s, k, r, sigma, t) - sigma * Math.sqrt(t);
    }

    public double deltaCall(double s, double k, double r, double sigma, double t) {
        return NormalDistributionUtil.cdf(dOne(s, k, r, sigma, t));
    }

    public double deltaPut(double s, double k, double r, double sigma, double t) {
        return NormalDistributionUtil.cdf(dOne(s, k, r, sigma, t)) - 1.0;
    }

    public double gamma(double s, double k, double r, double sigma, double t) {
        double d1 = dOne(s, k, r, sigma, t);
        return NormalDistributionUtil.pdf(d1) / (s * sigma * Math.sqrt(t));
    }

    public double vega(double s, double k, double r, double sigma, double t) {
        double d1 = dOne(s, k, r, sigma, t);
        return s * NormalDistributionUtil.pdf(d1) * Math.sqrt(t);
    }

    public double thetaCall(double s, double k, double r, double sigma, double t) {
        double d1 = dOne(s, k, r, sigma, t);
        double d2 = dTwo(s, k, r, sigma, t);
        return -(s * NormalDistributionUtil.pdf(d1) * sigma / (2 * Math.sqrt(t)))
                - r * k * Math.exp(-r * t) * NormalDistributionUtil.cdf(d2);
    }

    public double thetaPut(double s, double k, double r, double sigma, double t) {
        double d1 = dOne(s, k, r, sigma, t);
        double d2 = dTwo(s, k, r, sigma, t);
        return -(s * NormalDistributionUtil.pdf(d1) * sigma / (2 * Math.sqrt(t)))
                + r * k * Math.exp(-r * t) * NormalDistributionUtil.cdf(-d2);
    }

    public double rhoCall(double s, double k, double r, double sigma, double t) {
        double d2 = dTwo(s, k, r, sigma, t);
        return k * t * Math.exp(-r * t) * NormalDistributionUtil.cdf(d2);
    }

    public double rhoPut(double s, double k, double r, double sigma, double t) {
        double d2 = dTwo(s, k, r, sigma, t);
        return -k * t * Math.exp(-r * t) * NormalDistributionUtil.cdf(-d2);
    }
}