package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class TransactionFileManager {
    public static double total = 0;
    static ArrayList<Transaction> transactions = new ArrayList<>();

    private static final String FILE_PATH="src/main/resources/transactions.csv";


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

    public static void displayAllSorted(){

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        // Sort by date + time (newest first)
        transactions.sort((t1, t2) -> {
            String dt1 = t1.getDate() + " " + t1.getTime();
            String dt2 = t2.getDate() + " " + t2.getTime();
            return dt2.compareTo(dt1);
        });
        total=0;
        for (Transaction t : transactions) {
            System.out.println(t.toCSV());
            total+=t.getAmount();
        }




    }
    public static double getTotal() {
        return total;
    }

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
