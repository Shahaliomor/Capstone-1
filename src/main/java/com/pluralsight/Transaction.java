package com.pluralsight;

public class Transaction {
   private String date;
   private String time;
   private String description;
   private String vendor;
   private double amount;
   private double total;

    Transaction(String date, String time, String description, String vendor, double amount)
    {
        this.description=description;
        this.vendor=vendor;
        this.amount=amount;
        this.time= time;
        this.date= date;

    }



    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }



    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}
