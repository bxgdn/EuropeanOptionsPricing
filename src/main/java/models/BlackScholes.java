package models;

import utils.NormalDistributionUtil;

public class BlackScholes {

    public double priceCall(double s, double k, double r, double sigma, double t) {

        Greeks greeks = new Greeks();
        double dOneVal = greeks.dOne(s, k, r, sigma, t);
        double dTwoVal = greeks.dTwo(s, k, r, sigma, t);

        return (s * NormalDistributionUtil.cdf(dOneVal) - k * Math.exp(-r*t) * NormalDistributionUtil.cdf(dTwoVal));
    }

    public double pricePut(double s, double k, double r, double sigma, double t) {

        Greeks greeks = new Greeks();
        double dOneVal = greeks.dOne(s, k, r, sigma, t);
        double dTwoVal = greeks.dTwo(s, k, r, sigma, t);

        return (k * Math.exp(-r*t) * NormalDistributionUtil.cdf(-dTwoVal) - s * NormalDistributionUtil.cdf(-dOneVal));
    }
}