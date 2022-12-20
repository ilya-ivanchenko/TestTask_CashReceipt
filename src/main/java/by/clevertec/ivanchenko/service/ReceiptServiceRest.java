package by.clevertec.ivanchenko.service;

import by.clevertec.ivanchenko.model.Item;
import by.clevertec.ivanchenko.print.ReceiptPrint;

import by.clevertec.ivanchenko.util.ReceiptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceRest {
    double discountCard;
    double discountItem;
    double totalSum;

    ReceiptPrint receiptPrint = new ReceiptPrint();

    private final ItemService itemService;
    private final DiscountCardService discountCardService;

    @Autowired
    public ReceiptServiceRest(ItemService itemService, DiscountCardService discountCardService) {
        this.itemService = itemService;
        this.discountCardService = discountCardService;
    }

    public void setDefault() {
        discountCard = 1.0;
        totalSum = 0.0;
    }

// Data from DB:
    public void calculateReceipt(List<Integer> idList, List<Integer> qtyList, Integer card) {
        setDefault();
        receiptPrint.initialize();
        receiptPrint.printHeader();

        if (card != null && discountCardService.existsByNumber(card)) {
            discountCard = 0.7;                                                                                            // I decided our suggestion for regular customers must be extremely good
        }

        for (Integer id : idList) {
            Item currentItem = itemService.findOne(id);
            int quantity = qtyList.get(idList.indexOf(id));
            discountItem = 1.0;

            if (quantity > 5) {
                discountItem = 0.9;
            }
            double countPrice = currentItem.getPrice() * discountCard * discountItem;
            double countTotal = quantity * countPrice;
            totalSum += countTotal;
            receiptPrint.printItemLine(countPrice, countTotal, quantity, currentItem.getName());
        }
        receiptPrint.printTotal(totalSum);
    }
}

