package by.clevertec.ivanchenko;

import by.clevertec.ivanchenko.model.Item;
import by.clevertec.ivanchenko.util.ReceiptException;

import java.io.*;
import java.util.Arrays;

public class Receipt {

    public static void main(String[] args) throws ReceiptException {
        double discountCard = 1.0;
        double discountItem;
        double totalAll = 0.0;
        String delimeter = "-------------------------------------------------------";
        Item item = new Item();
        item.createItemList();
        item.createItemPrice();

        if (args.length == 1) {
            // Reading from file
            try (FileReader fileReader = new FileReader("../resources/" + args[0])) {

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                String[] data = line.split("\\s+");
                args = Arrays.copyOf(data, data.length);
            } catch (IOException exception) {
                throw new ReceiptException("Error while reading data from the file", exception);
            }
        }
        // Reading from console

        try (PrintStream printStream = new PrintStream("../resources/Receipt.txt")) {
            System.out.printf("%35s \n%s \n%-5s %-30s %7s %10s\n", "CASH RECEIPT", delimeter, "QTY", "DESCRIPTION", "PRICE", "TOTAL");
            printStream.printf("%35s \n%s \n%-5s %-30s %7s %10s\n", "CASH RECEIPT", delimeter, "QTY", "DESCRIPTION", "PRICE", "TOTAL");

            if ((args[args.length - 1]).matches("card-\\d+")) {
                discountCard = 0.7;                                                                                  // I decided our suggestion for regular customers must be extremely good
            }

            for (String element : args) {
                String[] array = element.split("-");
                if (array.length == 2) {
                    if (((array[0].matches("\\d+") && array[1].matches("\\d+")))) {
                        String currentItem = item.getItemList().get(Integer.valueOf(array[0]));
                        String quantity = array[1];
                        discountItem = 1.0;
                        if (currentItem == null) {
                            throw new ReceiptException("\nItem with id = " + array[0] + " wasn't found!");
                        }
                        if (Integer.parseInt(quantity) > 5) {
                            discountItem = 0.9;
                        }
                        double countPrice = item.getItemPrice().get(Integer.valueOf(array[0])) * discountCard * discountItem;
                        double countTotal = Integer.parseInt(quantity) * countPrice;
                        totalAll += countTotal;
                        String price = String.format("$%.2f", countPrice);
                        String total = String.format("$%.2f", countTotal);
                        System.out.printf("\n %-4s %-30s %7s %10s",
                                quantity, currentItem, price, total);
                        printStream.printf("\n %-4s %-30s %7s %10s",
                                quantity, currentItem, price, total);
                    } else if (!(args[args.length - 1]).matches("card-\\d+")) {
                        throw new ReceiptException("\nRequest must include numbers: number(id) - number(quantity) and optionally discount: 'card'-number");
                    }
                } else {
                    throw new ReceiptException("Check you request format!");
                }
            }
            String totalPrice = String.format("$%.2f", totalAll);
            System.out.println("\n" + delimeter);
            printStream.println("\n" + delimeter);
            System.out.printf("%-40s %14s", "TOTAL", totalPrice);
            printStream.printf("%-40s %14s", "TOTAL", totalPrice);
        } catch (IOException exception) {
            throw new ReceiptException("Error while writing receipt to the file", exception);
        }
    }
}


// TO DO:

// items,price in the code?
// Patterns (Factory, Builder, Decorator)
// README
// Unit tests
// Exceptions
// *PostgreSQL
// *RESTFUL
// *DOCKER
// *Add, improve smth
