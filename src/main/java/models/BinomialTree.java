package models;

public class BinomialTree {

    public double horizon(double t, int n) {
        return t / n;
    }

    public double upFactor(double t, int n, double sigma) {

        double deltaT = horizon(t, n);
        return Math.exp(sigma * Math.sqrt(deltaT));
    }

    public double downFactor(double t, int n, double sigma) {

        double up = upFactor(t, n, sigma);
        return 1 / up;
    }

    public double riskProbability(double t, int n, double sigma, double r) {

        double up  = upFactor(t, n, sigma);
        double down  = downFactor(t, n, sigma);
        double deltaT = horizon(t, n);
        return (Math.exp(r * deltaT) - down) / (up - down);
    }

    public double priceCall(double s, double k, double r, double sigma, double t, int n) {

        double deltaT = horizon(t, n);
        double up = upFactor(t, n, sigma);
        double down = downFactor(t, n, sigma);
        double risk =  riskProbability(t, n, r, sigma);

        double[] values = new double[n+1];

        for (int i = 0; i <= n; i++) {
            values[i] = Math.max(s * Math.pow(up, i) * Math.pow(down, n-i) - k, 0);
        }

        double discount = Math.exp(-r * deltaT);

        for (int step = n - 1; step >= 0; step--) {
            for (int j = 0; j <= step; j++) {
                values[j] = discount * (risk * values[j + 1] + (1 - risk) * values[j]);
            }
        }
        return values[0];
    }

    public double pricePut(double s, double k, double r, double sigma, double t, int n) {

        double deltaT = horizon(t, n);
        double up = upFactor(t, n, sigma);
        double down = downFactor(t, n, sigma);
        double risk =  riskProbability(t, n, r, sigma);

        double[] values = new double[n+1];

        for (int i = 0; i <= n; i++) {
            values[i] = Math.max(k - s * Math.pow(up, i) * Math.pow(down, n-i), 0);
        }

        double discount = Math.exp(-r * deltaT);

        for (int step = n - 1; step >= 0; step--) {
            for (int j = 0; j <= step; j++) {
                values[j] = discount * (risk * values[j + 1] + (1 - risk) * values[j]);
            }
        }
        return values[0];
    }

}