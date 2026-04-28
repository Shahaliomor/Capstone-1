package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
   private String date;
   private String time;
   private String description;
   private String vendor;
   private double amount;

    Transaction(String description, String vendor, double amount)
    {
        this.description=description;
        this.vendor=vendor;
        this.amount=amount;
        this.time= LocalTime.now().toString(); //Today's date
        this.date= LocalDate.now().toString(); //Current time

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

    public double getAmount() {
        return amount;
    }

    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}
