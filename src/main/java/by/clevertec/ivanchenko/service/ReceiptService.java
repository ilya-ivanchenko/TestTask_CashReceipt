package by.clevertec.ivanchenko.service;

import by.clevertec.ivanchenko.dao.ItemDAO;
import by.clevertec.ivanchenko.model.Item;
import by.clevertec.ivanchenko.print.ReceiptPrint;
//import by.clevertec.ivanchenko.repository.DiscountCardRepository;

import by.clevertec.ivanchenko.util.ReceiptException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ReceiptService {
    double discountCard = 1.0;
    double discountItem;
    double totalSum = 0.0;

    Item item = new Item();
    ReceiptPrint receiptPrint = new ReceiptPrint();

    // Data from collections:

//    public void calculateReceipt(String[] args) throws ReceiptException {
//        item.createItemList();
//        item.createItemPrice();
//
//        if (args[0].matches(".+txt")) {
//// Reading from file
//            try (FileReader fileReader = new FileReader("../resources/" + args[0])) {
//
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                String line = bufferedReader.readLine();
//                String[] data = line.split("\\s+");
//                args = Arrays.copyOf(data, data.length);
//            } catch (IOException exception) {
//                throw new ReceiptException("Error while reading data from the file", exception);
//            }
//        }
//
//// Reading from console
//        receiptPrint.initialize();
//        receiptPrint.printHeader();
//        if ((args[args.length - 1]).matches("card-\\d+")) {
//                                                                                                                    // EDIT: Check on existing card in DB
//            discountCard = 0.7;                                                                                  // I decided our suggestion for regular customers must be extremely good
//        }
//
//        for (String element : args) {
//            String[] array = element.split("-");
//
//            if (array.length == 2) {
//                if (((array[0].matches("\\d+") && array[1].matches("\\d+")))) {
//                    String currentItem = item.getItemList().get(Integer.valueOf(array[0]));
//                     int quantity = Integer.parseInt(array[1]);
//                    discountItem = 1.0;
//
//                    if (currentItem == null) {
//                        throw new ItemNotFoundException("\nItem with id = " + array[0] + " wasn't found!");
//                    }
//                    if (quantity > 5) {
//                        discountItem = 0.9;
//                    }
//                    double countPrice = item.getItemPrice().get(Integer.valueOf(array[0])) * discountCard * discountItem;
//                    double countTotal = quantity * countPrice;
//                    totalSum += countTotal;
//                    receiptPrint.printItemLine(countPrice, countTotal, quantity, currentItem);
//                } else if (!(args[args.length - 1]).matches("card-\\d+")) {
//                    throw new ReceiptException("\nRequest must include numbers: number(id) - number(quantity) and optionally discount: 'card'-number");
//                }
//            } else {
//                throw new ReceiptException("Check you request format!");
//            }
//        }
//        receiptPrint.printTotal(totalSum);
//    }






// Data from DB:
ItemDAO itemDAO = new ItemDAO();
public void calculateReceipt(String[] args) throws ReceiptException {

    if (args[0].matches(".+txt")) {
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
    receiptPrint.initialize();
    receiptPrint.printHeader();
    if ((args[args.length - 1]).matches("card-\\d+")) {
        // EDIT: Check on existing card in DB
        discountCard = 0.7;                                                                                  // I decided our suggestion for regular customers must be extremely good
    }

    for (String element : args) {
        String[] array = element.split("-");

        if (array.length == 2) {
            if (((array[0].matches("\\d+") && array[1].matches("\\d+")))) {
                Item currentItem = itemDAO.getItem(Integer.parseInt(args[0]));
                int quantity = Integer.parseInt(array[1]);
                discountItem = 1.0;

                if (currentItem == null) {
                    throw new ReceiptException("\nItem with id = " + array[0] + " wasn't found!");
                }
                if (quantity > 5) {
                    discountItem = 0.9;
                }
                double countPrice = currentItem.getPrice() * discountCard * discountItem;
                double countTotal = quantity * countPrice;
                totalSum += countTotal;
                receiptPrint.printItemLine(countPrice, countTotal, quantity, currentItem.getName());
            } else if (!(args[args.length - 1]).matches("card-\\d+")) {
                throw new ReceiptException("\nRequest must include numbers: number(id) - number(quantity) and optionally discount: 'card'-number");
            }
        } else {
            throw new ReceiptException("Check you request format!");
        }
    }
    receiptPrint.printTotal(totalSum);
}
}
