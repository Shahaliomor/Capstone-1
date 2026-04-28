package com.pluralsight;

import java.util.Scanner;

public class AccountingLedgerApp {
    static Scanner input= new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();
    }

    private static void homeScreen(){

        while (true){
            System.out.println("""
                    D) Add Deposit
                    P) Make Payment
                    L) Ledger
                    X) Exit
                    """);
            System.out.print("choose: ");
            String choose=input.nextLine().trim();
            if(choose.equalsIgnoreCase("d")){
                addDeposit();
            } else if (choose.equalsIgnoreCase("p")) {
                makePayment();
            }else if (choose.equalsIgnoreCase("l")) {
                ledger();
            }else if (choose.equalsIgnoreCase("x")) {
                System.out.println("Thank you for using the Accounting Ledger App. Your transactions have been recorded successfully. Have a great day!");
                return;
            }
            else {
                System.out.println();
                System.out.println("Invalid input. Please choose from the options below:");

            }

        }

    }
    private static String getDate() {
        System.out.print("Year (YYYY): ");
        String year = input.nextLine();

        System.out.print("Month (MM): ");
        String month = input.nextLine();

        System.out.print("Day (DD): ");
        String day = input.nextLine();

        return year + "-" + month + "-" + day;
    }
    private static String getTime() {
        System.out.print("Hour (HH): ");
        String hour = input.nextLine();

        System.out.print("Minute (MM): ");
        String minute = input.nextLine();

        System.out.print("Second (SS): ");
        String second = input.nextLine();

        return hour + ":" + minute + ":" + second;
    }

    private static String getInput(String message) {
        System.out.print(message);
        String value = input.nextLine().trim();
        System.out.println();
        return value;
    }
    private static void addDeposit(){
        String date = getDate();
        String time = getTime();
        String description = getInput("Description: ");
        String vendor = getInput("Vendor: ");


        double amount;

        while (true) {
            System.out.print("Amount: $");

            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                input.nextLine();

                if (amount > 0) {
                    break; // valid deposit
                } else {
                    System.out.println("Negative amount cannot be a deposit. Please enter a positive amount.");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // clear bad input
            }
        }

        Transaction trans= new Transaction(date,time,description,vendor,amount);
        TransactionFileManager.writefile(trans.toCSV());

        System.out.println("Deposit added successfully!");
        System.out.println(" ");

    }
    private static void makePayment(){
        String date = getDate();
        String time = getTime();
        String description = getInput("Description: ");
        String vendor = getInput("Vendor: ");


        double amount;

        while (true) {
            System.out.print("Amount: $");

            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                input.nextLine();

                if (amount < 0) {
                    break; // valid deposit
                } else {
                    amount = -Math.abs(amount);
                    break;
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // clear bad input
            }
        }

        Transaction trans= new Transaction(date,time,description,vendor,amount);
        TransactionFileManager.writefile(trans.toCSV());

        System.out.println("Payment added successfully!");
        System.out.println(" ");


    }
    private static void ledger(){

    }

}


