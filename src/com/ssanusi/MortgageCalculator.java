package com.ssanusi;

public class MortgageCalculator {
    private final static byte MONTH_IN_YEARS = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterestRate;
    private short years;

    public MortgageCalculator(int principal, float annualInterestRate, short years) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.years = years;
    }

    public double calculateMortgage() {
        short numberOfPayment = getNumberOfPayment();
        float monthlyInterestRate = getMonthlyInterestRate();
        return principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayment))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayment) - 1);
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayment()];
        for (short month = 0; month <= balances.length; month++)
            balances[month] = calculateBalance(month);

        return balances;
    }

    private float getMonthlyInterestRate() {
        return annualInterestRate / PERCENT / MONTH_IN_YEARS;
    }

    public double calculateBalance(short numberOfPaymentMade) {
        short numberOfPayment = getNumberOfPayment();
        float monthlyInterestRate = getMonthlyInterestRate();

        return principal
                * (Math.pow(1 + monthlyInterestRate, numberOfPayment) - Math.pow(1 + monthlyInterestRate, numberOfPaymentMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayment) - 1);

    }

    private short getNumberOfPayment() {
        return (short) (years * MONTH_IN_YEARS);
    }

}
