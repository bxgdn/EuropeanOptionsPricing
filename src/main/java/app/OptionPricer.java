package app;

import models.*;
import javax.swing.*;
import java.awt.*;

public class OptionPricer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OptionPricer::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Option Pricer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        JTextField sField = new JTextField();
        JTextField kField = new JTextField();
        JTextField rField = new JTextField();
        JTextField sigmaField = new JTextField();
        JTextField tField = new JTextField();
        JTextField nField = new JTextField();

        String[] models = {"Black-Scholes", "Binomial Tree", "Monte Carlo"};
        JComboBox<String> modelBox = new JComboBox<>(models);

        String[] optionTypes = {"Call", "Put"};
        JComboBox<String> optionBox = new JComboBox<>(optionTypes);

        JLabel resultLabel = new JLabel("Price: ");
        JButton calcButton = new JButton("Calculate");

        panel.add(new JLabel("Spot Price (S):")); panel.add(sField);
        panel.add(new JLabel("Strike Price (K):")); panel.add(kField);
        panel.add(new JLabel("Risk-Free Rate (r):")); panel.add(rField);
        panel.add(new JLabel("Volatility (σ):")); panel.add(sigmaField);
        panel.add(new JLabel("Time to Maturity (T):")); panel.add(tField);
        panel.add(new JLabel("Steps/Simulations (N):")); panel.add(nField);
        panel.add(new JLabel("Model:")); panel.add(modelBox);
        panel.add(new JLabel("Option Type:")); panel.add(optionBox);

        panel.add(calcButton);
        panel.add(resultLabel);

        calcButton.addActionListener(e -> {
            try {
                double s = Double.parseDouble(sField.getText());
                double k = Double.parseDouble(kField.getText());
                double r = Double.parseDouble(rField.getText());
                double sigma = Double.parseDouble(sigmaField.getText());
                double t = Double.parseDouble(tField.getText());
                int n = Integer.parseInt(nField.getText());

                String selectedModel = (String) modelBox.getSelectedItem();
                String optionType = (String) optionBox.getSelectedItem();

                double price = 0.0;

                switch (selectedModel) {
                    case "Black-Scholes" -> {
                        BlackScholes bs = new BlackScholes();
                        price = optionType.equals("Call")
                                ? bs.priceCall(s, k, r, sigma, t)
                                : bs.pricePut(s, k, r, sigma, t);
                    }
                    case "Binomial Tree" -> {
                        BinomialTree bt = new BinomialTree();
                        price = optionType.equals("Call")
                                ? bt.priceCall(s, k, r, sigma, t, n)
                                : bt.pricePut(s, k, r, sigma, t, n);
                    }
                    case "Monte Carlo" -> {
                        MonteCarlo mc = new MonteCarlo();
                        price = optionType.equals("Call")
                                ? mc.priceCallMc(s, k, r, sigma, t, n)
                                : mc.pricePutMc(s, k, r, sigma, t, n);
                    }
                }

                resultLabel.setText("Price: " + String.format("%.4f", price));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers only.");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}