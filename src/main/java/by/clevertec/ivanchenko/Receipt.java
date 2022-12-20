package by.clevertec.ivanchenko;

import by.clevertec.ivanchenko.service.ReceiptService;

public class Receipt {

    public static void main(String[] args)  {

         ReceiptService receiptService = new ReceiptService();
        receiptService.calculateReceipt(args);
    }
}

// TO DO:

// Patterns (Factory, Builder, Decorator)
// README
// Unit tests
// *DOCKER
// *Add, improve smth
