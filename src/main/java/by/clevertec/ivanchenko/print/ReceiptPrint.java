package by.clevertec.ivanchenko.print;

import by.clevertec.ivanchenko.util.OutputFileNotFoundException;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ReceiptPrint {
    String delimeter = "-------------------------------------------------------";
    PrintStream printStream;

    public void initialize() {
        try {
            printStream = new PrintStream("src/main/resources/Receipt.txt");
        } catch (FileNotFoundException exp) {
            throw new OutputFileNotFoundException("Error: can't find output file", exp);
        }
    }

    public void printHeader() {
        System.out.printf("%35s \n%s \n%-5s %-30s %7s %10s\n", "CASH RECEIPT", delimeter, "QTY", "DESCRIPTION", "PRICE", "TOTAL");
        printStream.printf("%35s \n%s \n%-5s %-30s %7s %10s\n", "CASH RECEIPT", delimeter, "QTY", "DESCRIPTION", "PRICE", "TOTAL");
    }

    public void printItemLine(double countPrice, double countTotal, int quantity, String currentItem) {
        String price = String.format("$%.2f", countPrice);
        String total = String.format("$%.2f", countTotal);
        System.out.printf("\n %-4s %-30s %7s %10s", quantity, currentItem, price, total);
        printStream.printf("\n %-4s %-30s %7s %10s", quantity, currentItem, price, total);
    }

    public void printTotal(double totalSum) {
        String totalPrice = String.format("$%.2f", totalSum);
        System.out.println("\n" + delimeter);
        printStream.println("\n" + delimeter);
        System.out.printf("%-40s %14s", "TOTAL", totalPrice);
        printStream.printf("%-40s %14s", "TOTAL", totalPrice);
    }
}

