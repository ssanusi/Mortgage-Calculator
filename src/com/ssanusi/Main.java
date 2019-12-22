package com.ssanusi;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    final byte MONTH_IN_YEARS = 12;
	    final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = scanner.nextFloat();
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTH_IN_YEARS;

        System.out.println("Period (Years): ");
        byte period = scanner.nextByte();
        int numberOfPayment = period * MONTH_IN_YEARS;


        double mortgage = principal *  (monthlyInterestRate * Math.pow(1 + monthlyInterestRate,numberOfPayment))
                / (Math.pow(1+ monthlyInterestRate, numberOfPayment) - 1);
        String result = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: "+ result);

    }
}
