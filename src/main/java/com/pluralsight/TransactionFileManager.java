package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
// This class handles all file operations related to transactions
public class TransactionFileManager {
    public static double total = 0;  // Stores total balance after calculations
    // List to hold all transactions loaded from file
    static ArrayList<Transaction> transactions = new ArrayList<>();
    // File path where transactions are stored
    private static final String FILE_PATH="src/main/resources/transactions.csv";
    // Sort transactions by date and time
    public static void sortTransactions(){
        transactions.sort((t1, t2) -> {
            String dt1 = t1.getDate() + " " + t1.getTime();
            String dt2 = t2.getDate() + " " + t2.getTime();
            return dt2.compareTo(dt1);
        });
    }
    // Read all transactions from CSV file and store in ArrayList
    public static void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            transactions.clear();
            String line;
            while ((line=reader.readLine())!=null) {
                String[] parts=line.split("\\|");
                String date=parts[0];
                String time=parts[1];
                String description=parts[2];
                String vandor=parts[3];
                double amount=Double.parseDouble(parts[4]);
                Transaction trans=new Transaction(date,time,description,vandor,amount);
                transactions.add(trans);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error: file not found");
        } catch (IOException e){
            System.out.print("Error: IO Exception");
        }
    }
    // Display all transactions sorted by newest first and calculate total
    public static void displayAllSorted(){
        // Check if list is empty
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        // Sort transactions before displaying
        sortTransactions();
        total=0;
        // Loop through all transactions and print them
        for (Transaction t : transactions) {
            System.out.println(t.toCSV());
            total+=t.getAmount();
        }
    }
    // Return total balance
    public static double getTotal() {
        return total;
    }
    // Write a new transaction line into the CSV file (append mode)
    public static void writefile(String transactionLine){
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(FILE_PATH,true));
            write.write(transactionLine);   // write data
            write.newLine();                // move to next line
            write.close();                  // close file

        } catch (FileNotFoundException e) {
            System.out.print("Error: file not found");
        }catch (IOException e){
            System.out.print("Error: IO Exception");
        }
    }
}
