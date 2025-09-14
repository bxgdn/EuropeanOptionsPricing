package utils;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionUtil {

    public static double pdf(double x) {
        return ((1 / Math.sqrt(2*Math.PI)) * (Math.exp(-x*x/2)));
    }

    public static double cdf(double x) {

        final double p = 0.2316419;
        final double aOne = 0.319381530;
        final double aTwo = -0.356563782;
        final double aThree = 1.781477937;

        final boolean isNegative = x < 0.0;

        if(isNegative) {
            x = -x;
        }

        double t = (1/(1+p*x));

        double pdfUtil = (1 / Math.sqrt(2*Math.PI)) * (Math.exp(-x*x/2));

        double poly = (aOne * t + aTwo * Math.pow(t,2) + aThree * Math.pow(t,3));

        double result = (1 - pdfUtil * poly);

        if (isNegative) {
            result = 1.0 - result;
        }

        return result;
    }

}