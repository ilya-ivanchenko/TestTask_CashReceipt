package by.clevertec.ivanchenko;

import by.clevertec.ivanchenko.service.ReceiptService;
import by.clevertec.ivanchenko.util.ReceiptException;

public class Receipt {

    public static void main(String[] args) throws ReceiptException {

        ReceiptService receiptService = new  ReceiptService();
        receiptService.calculateReceipt(args);
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
