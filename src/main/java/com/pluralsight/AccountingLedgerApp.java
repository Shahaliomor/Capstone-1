package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountingLedgerApp {
    static Scanner input= new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Accounting Ledger App!");
        homeScreen();
    }

    private static void pause(){
        System.out.println("\n🥷 Press Enter to continue...");
        input.nextLine(); // clear leftover

    }

    private static void homeScreen(){

        while (true){
            System.out.println("""
            ====================================
                    EXPENSE NINJA 🥷
            ====================================
            Track. Control. Master your money.
            ------------------------------------
            D) Add Deposit
            P) Make Payment
            L) Ledger
            X) Exit
            ====================================
            """);
            System.out.print("Choose an option: ");
            String choose=input.nextLine().trim();
            if(choose.equalsIgnoreCase("d")){
                addDeposit();
            } else if (choose.equalsIgnoreCase("p")) {
                makePayment();
            }else if (choose.equalsIgnoreCase("l")) {
                ledger();
            }else if (choose.equalsIgnoreCase("x")) {
                System.out.println("""
                Thank you for using the EXPENSE NINJA App 🥷. 
                Your transactions have been recorded successfully 🥷. 
                Have a great day 🥷!
                """);
                return;
            }
            else {
                System.out.println();
                System.out.println("❌ Invalid choice. Please enter D, P, L, or X.");

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

    private static String currentDateAndTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        String dateTime = LocalDateTime.now().format(formatter);

        return dateTime;

    }

    private static String getInput(String message) {
        System.out.print(message);
        String value = input.nextLine().trim();
        System.out.println();
        return value;
    }
    private static void addDeposit(){

        System.out.println("""
        ------------------------------------
                  🥷 ADD DEPOSIT
        ------------------------------------
        """);

        String date = getDate();
        String time = getTime();
        String description = getInput("Description: ");
        String vendor = getInput("Vendor: ");


        double amount;

        while (true) {
            System.out.println();
            System.out.print("Amount: $");

            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                input.nextLine();

                if (amount > 0) {
                    break; // valid deposit
                } else {
                    System.out.println("❌ Negative amount cannot be a deposit. Enter a positive value.");
                }

            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                input.nextLine(); // clear bad input
            }
        }

        Transaction trans= new Transaction(date,time,description,vendor,amount);
        TransactionFileManager.writefile(trans.toCSV());

        System.out.println("✅ Deposit saved successfully!");
        pause();

    }
    private static void makePayment(){

        System.out.println("""
    ------------------------------------
               🥷 MAKE PAYMENT
    ------------------------------------
    """);

        String date = getDate();
        String time = getTime();
        String description = getInput("Description: ");
        String vendor = getInput("Vendor: ");

        double amount;

        while (true) {
            System.out.println();
            System.out.print("Amount: $");

            if (input.hasNextDouble()) {
                amount = input.nextDouble();
                input.nextLine();

                if (amount == 0) {
                    System.out.println("❌ Amount cannot be zero.");
                    continue;
                }

                amount = -Math.abs(amount); // 🔥 always negative
                break;

            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                input.nextLine();
            }
        }

        Transaction trans = new Transaction(date, time, description, vendor, amount);
        TransactionFileManager.writefile(trans.toCSV());

        // 👇 ADD THIS (presentation boost)
        System.out.println("\nSaved Transaction:");
        System.out.println(trans.toCSV());

        System.out.println("✅ Payment saved successfully!\n");
        pause();
    }

    private static void ledger() {

        System.out.println();
        System.out.println();

        while (true) {

            System.out.println("""
                
                ====================================
                            🥷 LEDGER 
                ====================================
                A) All - Display all entries
                D) Deposits - Display only the entries that are deposits into the account
                P) Payments - Display payment
                R) Reports
                H) Home - go back to the home page
                ====================================
                """);

            System.out.print("choose: ");
            String choose=input.nextLine().trim();
            if(choose.equalsIgnoreCase("a")){
                displayAll();
            } else if (choose.equalsIgnoreCase("d")) {
                displayDeposits();
            }else if (choose.equalsIgnoreCase("p")) {
                displayPayments();
            }else if (choose.equalsIgnoreCase("r")) {
                reports();
            }
            else if (choose.equalsIgnoreCase("h")) {
                System.out.println("Going back to main screen");
                System.out.println();
                System.out.println();
                return;
            }
            else {
                System.out.println();
                System.out.println("❌ Invalid choice. Please enter A, D, P, R or H.");
            }
        }
    }

    private static void displayAll(){


        System.out.println("""
                
                ====================================================
                🥷 Display all entries in reverse chronological order
                ====================================================
                """);
        TransactionFileManager.readFile();
        TransactionFileManager.displayAllSorted();
        System.out.println("----------------------------------------------------");
        System.out.printf("🥷 Remaining Balance as of %s : $%.2f%n", currentDateAndTime(), TransactionFileManager.getTotal());

        System.out.println();
        System.out.println("====================================================");
        pause();

    }
    private static void displayDeposits(){
        System.out.println("""
            
            ====================================================
                        🥷 DEPOSITS (Income Only)
            ====================================================
            """);

        TransactionFileManager.readFile();

        boolean found = false;
        double total = 0;

        for (Transaction t : TransactionFileManager.transactions){
            if (t.getAmount() > 0){
                System.out.println(t.toCSV());
                total += t.getAmount();
                found = true;
            }
        } //  loop ends here

        if (!found){
            System.out.println("No deposits found.");
        }

        System.out.println("---------------------------------------------------");
        System.out.printf("🥷 Total Deposits as of %s : $%.2f%n", currentDateAndTime(), total);
        System.out.println("====================================================");

        pause();
    }
    private static void displayPayments(){
        System.out.println("""
            
            ====================================================
                        🥷 Payment (Payments Only)
            ====================================================
            """);

        TransactionFileManager.readFile();

        boolean found = false;
        double total = 0;

        for (Transaction t : TransactionFileManager.transactions){
            if (t.getAmount() < 0){
                System.out.println(t.toCSV());
                total += t.getAmount();
                found = true;
            }
        } // ✅ loop ends here

        if (!found){
            System.out.println("No Payment found.");
        }

        System.out.println("---------------------------------------------------");
        System.out.printf("🥷 Total Payments as of %s : $%.2f%n", currentDateAndTime(), total);
        System.out.println("====================================================");

        pause();

    }
    private static void reports(){

        while (true){
            System.out.println();
            System.out.println();
            System.out.println("""
                
                ====================================
                          🥷 REPORTS
                ====================================
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                0) Back
                ====================================
                """);

            System.out.print("Choose: ");
            String choice = input.nextLine().trim();

            if(choice.equals("1")){
                monthToDate();
            } else if(choice.equals("2")){
                previousMonth();
            } else if(choice.equals("3")){
                yearToDate();
            } else if(choice.equals("4")){
                previousYear();
            } else if(choice.equals("5")){
                searchByVendor();
            } else if(choice.equals("0")){
                return; // goes back to Ledger screen
            } else if (choice.equals("6")) {
                customSearch();

            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void monthToDate() {

    }
    private static void previousMonth(){

    }
    private static void yearToDate(){

    }
    private static void previousYear(){
        boolean found=false;
        double total=0;
        System.out.println("""
            
            ====================================================
                        🥷 Previous Year
            ====================================================
            """);

        int year= LocalDate.now().getYear();
        int previousYear=year-1;

        TransactionFileManager.readFile();
        for (Transaction t:TransactionFileManager.transactions) {
            int transactionYear=Integer.parseInt(t.getDate().substring(0,4));

            if (transactionYear==previousYear) {
                System.out.println(t.toCSV());
                total += t.getAmount();
                found = true;
            }
        }
        if(!found){
            System.out.println("No Vabdor found.");
        }

        System.out.println("---------------------------------------------------");
        System.out.printf("🥷 Total Payments as of %s : $%.2f%n", currentDateAndTime(), total);
        System.out.println("====================================================");
        pause();

    }
    private static void searchByVendor(){

        boolean found=false;
        double total=0;
        System.out.println("""
            
            ====================================================
                        🥷 Search By Vendor
            ====================================================
            """);


        TransactionFileManager.readFile();
        System.out.print("which vandor are you searching: ");
        String vandorName=input.nextLine();
        for (Transaction t:TransactionFileManager.transactions) {
            if (t.getVendor().equalsIgnoreCase(vandorName)) {
                System.out.println(t.toCSV());
                total += t.getAmount();
                found = true;
            }
        }
            if(!found){
                System.out.println("No Vabdor found.");
            }

        System.out.println("---------------------------------------------------");
        System.out.printf("🥷 Total Payments as of %s : $%.2f%n", currentDateAndTime(), total);
        System.out.println("====================================================");
        pause();

    }
    private static void customSearch(){

    }

}


