package com.pluralsight;

import java.io.*;

public class TransactionFileManager {

    private static final String FILE_PATH="src/main/resources/transactions.csv";

    public static void readfile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));


        } catch (FileNotFoundException e) {
            System.out.print("Error: file not found");
        }

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
