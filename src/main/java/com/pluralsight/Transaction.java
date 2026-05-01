package com.pluralsight;
// Transaction class represents a single financial record
public class Transaction {
   private String date; // Date of the transaction
   private String time; // Time of the transaction
   private String description; // Description of the transaction
   private String vendor; // Vendor or source of transaction
   private double amount; // Amount of transaction

    // Parameterized constructor: used to create a transaction with all details
   public Transaction(String date, String time, String description, String vendor, double amount)
    {
        this.description=description;
        this.vendor=vendor;
        this.amount=amount;
        this.time= time;
        this.date= date;
    }
    // Getter method for transaction date
    public String getDate() {
        return date;
    }
    // Getter method for transaction time
    public String getTime() {
        return time;
    }
    // Getter method for description
    public String getDescription() {
        return description;
    }
    // Getter method for vendor
    public String getVendor() {
        return vendor;
    }
    // Getter method for amount
    public double getAmount() {
        return amount;
    }


    // Converts transaction object into CSV format for file storage
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}
