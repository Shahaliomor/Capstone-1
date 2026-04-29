package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class TransactionFileManager {
    static  ArrayList<String> arrlines =new ArrayList<>();

    private static final String FILE_PATH="src/main/resources/transactions.csv";

    public static void readfile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line=reader.readLine())!=null) {
                arrlines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error: file not found");
        } catch (IOException e){
            System.out.print("Error: IO Exception");
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
