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
        int year;
        int month;
        int day;

        while (true) {

            System.out.print("Year (YYYY): ");
            if (input.hasNextInt()) {
                year = input.nextInt();
                if (year < 1900 || year > 2100) {
                    System.out.println("Invalid year. Try again.");
                    continue;
                }
            } else {
                System.out.println("Invalid input.");
                input.nextLine();
                continue;
            }

            System.out.print("Month (1-12): ");
            if (input.hasNextInt()) {
                month = input.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Try again.");
                    continue;
                }
            } else {
                System.out.println("Invalid input.");
                input.nextLine();
                continue;
            }

            System.out.print("Day (1-31): ");
            if (input.hasNextInt()) {
                day = input.nextInt();
                if (day < 1 || day > 31) {
                    System.out.println("Invalid day. Try again.");
                    continue;
                }
            } else {
                System.out.println("Invalid input.");
                input.nextLine();
                continue;
            }

            input.nextLine(); // clear buffer
            break;
        }

        return String.format("%04d-%02d-%02d", year, month, day);
    }
    private static String getTime() {
        int hour;
        int minute;
        int second;

        while (true) {

            System.out.print("Hour (0-23): ");
            hour = input.nextInt();
            if (hour < 0 || hour > 23) {
                System.out.println("Invalid hour. Try again.");
                continue;
            }

            System.out.print("Minute (0-59): ");
            minute = input.nextInt();
            if (minute < 0 || minute > 59) {
                System.out.println("Invalid minute. Try again.");
                continue;
            }

            System.out.print("Second (0-59): ");
            second = input.nextInt();
            if (second < 0 || second > 59) {
                System.out.println("Invalid second. Try again.");
                continue;
            }

            input.nextLine(); // clear buffer
            break; // ✅ valid input → exit loop
        }

        return String.format("%02d:%02d:%02d", hour, minute, second);
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


