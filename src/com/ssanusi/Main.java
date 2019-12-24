package com.ssanusi;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTH_IN_YEARS = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {
         int principal = (int)readNumber("Principal ($1k - $1M): ", 1000, 1_000_000);
         float annualInterestRate = (float)readNumber("Annual Interest Rate: ",0,30);
         byte years = (byte)readNumber("Period (Years): ", 1,30);

        printMortgage(principal, annualInterestRate, years);
        printPaymentSchedule(principal, annualInterestRate, years);
    }

    public static void printMortgage(int principal, float annualInterestRate, byte years) {
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        String result = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Mortgage: "+ result);
    }

    public static void printPaymentSchedule(int principal, float annualInterestRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("---------------- ");
        for (short month = 0; month <= years * MONTH_IN_YEARS; month++) {
            double balance = calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(int principal, float annualInterestRate, int years) {
        short numberOfPayment = (short)(years * MONTH_IN_YEARS);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTH_IN_YEARS;
        return principal *  (monthlyInterestRate * Math.pow(1 + monthlyInterestRate,numberOfPayment))
                    / (Math.pow(1+ monthlyInterestRate, numberOfPayment) - 1);
    }

    public static double calculateBalance(int principal, float annualInterestRate, int years, short numberOfPaymentMade){
        short numberOfPayment = (short)(years * MONTH_IN_YEARS);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTH_IN_YEARS;

        double balance = principal
                * (Math.pow(1 + monthlyInterestRate,numberOfPayment) - Math.pow(1 + monthlyInterestRate, numberOfPaymentMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayment) - 1);

        return balance;
    }

    public static double readNumber(String prompt, double min, double max){
        double value;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max)
                break;
            System.out.println("Enter A Value between "+min+" and "+max+":");
        }
        return value;
    }


}



