# 📈 Option Pricer
A **Java-based options pricing engine** that supports **Black–Scholes**, **Binomial Tree**, and **Monte Carlo Simulation** models for pricing European and American options. Includes support for **Greeks** (Δ, Γ, Θ, ρ, Vega) and uses Apache Commons Math for random number generation.

## 🚀 Features
- **Black–Scholes Pricing**  
  - Call and Put prices for European options.  
  - Delta, Gamma, Vega, Theta, Rho calculation.  
- **Monte Carlo Simulation**  
  - European Call & Put pricing.  
  - Configurable number of simulations.  
  - Mersenne Twister RNG for reproducibility.  
- **Binomial Tree Model**  
  - Call & Put pricing via backward induction.  
  - Adjustable number of steps (supports thousands of steps).  
  - Easily extendable for American-style options.  
- **Greeks Calculation**  
  - Analytical formulas for all primary Greeks.  
  - Useful for hedging and risk management.  
- **Extensible Design**  
  - Modular classes (`Greeks`, `NormalDistributionUtil`, `BlackScholes`, `MonteCarlo`, `BinomialTree`).  
  - Clean separation between pricing logic and UI.

## 🏗 Project Structure
```

src/
├─ models/
│   ├─ BlackScholes.java       # Closed-form pricing model
│   ├─ BinomialTree.java       # Tree-based pricing model
│   ├─ MonteCarlo.java         # Monte Carlo simulation model
│   └─ Greeks.java             # Greeks calculation formulas
│
├─ utils/
│   ├─ NormalDistributionUtil.java # PDF & CDF implementation
│   └─ RandomGenerator.java        # RNG using Mersenne Twister
│
└─ OptionPricer.java          # Main entry point / GUI launcher

````

## 📚 Formulas Implemented

### 1. Black–Scholes Model

**Call price:**

$$
C = S \cdot N(d_1) - K \cdot e^{-rT} \cdot N(d_2)
$$

**Put price:**

$$
P = K \cdot e^{-rT} \cdot N(-d_2) - S \cdot N(-d_1)
$$

where:

$$
d_1 = \frac{\ln(S/K) + (r + 0.5\sigma^2)T}{\sigma \sqrt{T}}, \quad
d_2 = d_1 - \sigma \sqrt{T}
$$

### 2. Monte Carlo Simulation

Simulated price at expiry:

$$
S_T = S \cdot e^{(r - \frac{1}{2}\sigma^2)T + \sigma \sqrt{T} Z}
$$

Option payoff:

$$
C = e^{-rT} \cdot \frac{1}{N} \sum_{i=1}^{N} \max(S_T^{(i)} - K, 0)
$$

### 3. Binomial Tree

At maturity:

$$
V_i = \max(S \cdot u^i d^{N-i} - K, 0)
$$

Backward induction step:

$$
V_j = e^{-r \Delta t} \big[p \cdot V_{j+1}^{\text{up}} + (1-p) \cdot V_{j}^{\text{down}}\big]
$$

where \(p\) is the **risk-neutral probability**:

$$
p = \frac{e^{r \Delta t} - d}{u - d}
$$


## 🖥 GUI
- Implemented a swing GUI
- Inputs: `S`, `K`, `r`, `σ`, `T`, number of steps/simulations.
- Dropdown to select **Black–Scholes**, **Binomial Tree**, or **Monte Carlo**.
- Display **Call/Put prices** and **Greeks**.
- Optionally, add **heatmaps** for price sensitivity (strike vs. volatility).

## 🔧 Installation & Usage

### 1. Clone the Repo
```bash
git clone https://github.com/yourusername/option-pricer-java.git
cd option-pricer-java
````

### 2. Compile and Run

```bash
javac -cp .:libs/commons-math3-3.6.1.jar src/**/*.java
java -cp .:libs/commons-math3-3.6.1.jar OptionPricer
```

> **Note:** Make sure you have [Apache Commons Math](https://commons.apache.org/proper/commons-math/) in your `libs/` folder.

## 🧪 Example Values for Testing

| Parameter            | Value                 |
| -------------------- | --------------------- |
| Spot Price (S)       | `100`                 |
| Strike (K)           | `100`                 |
| Risk-free Rate (r)   | `0.05`                |
| Volatility (σ)       | `0.2`                 |
| Time to Maturity (T) | `1` year              |
| Simulations (MC)     | `100,000 – 1,000,000` |
| Steps (Tree)         | `500 – 5,000`         |

## 📊 Performance Notes

* **Monte Carlo:** Scales linearly with number of simulations. ⚡ With 1M simulations on a modern CPU, should run under \~1s.
* **Binomial Tree:** Complexity grows **quadratically** with number of steps. Avoid `n > 50,000` for smooth performance.

## 🛠 Future Work

* ✅ American Option Support (Early Exercise)
* ✅ Parallelization for Monte Carlo & Tree
* ✅ Variance Reduction (Antithetic Sampling, Control Variates)
* ✅ GUI Enhancements (charts, heatmaps, input validation)

## 📜 License

MIT License – free to use, modify, and distribute.
