package by.clevertec.ivanchenko.model;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private final Map<Integer, String> itemList = new HashMap<>();
    private final Map<Integer, Double> itemPrice = new HashMap<>();

    public Map<Integer, String> getItemList() {
        return itemList;
    }

    public Map<Integer, Double> getItemPrice() {
        return itemPrice;
    }

    public void createItemList() {
        itemList.put(1, "Shampoo 500ml");
        itemList.put(2, "Deodorant 150ml");
        itemList.put(3, "Drinking water 1.5l");
        itemList.put(4, "Banana");
        itemList.put(5, "Cookie");
        itemList.put(6, "Dark chocolate");
        itemList.put(7, "Georgian red wine");
        itemList.put(8, "Beer 0.5l");
        itemList.put(9, "Toothpaste");
        itemList.put(10, "Book 'Java. Herbert Schildt'");
    }

    public void createItemPrice() {
        itemPrice.put(1, 3.95);
        itemPrice.put(2, 2.99);
        itemPrice.put(3, 0.99);
        itemPrice.put(4, 2.45);
        itemPrice.put(5, 1.93);
        itemPrice.put(6, 4.05);
        itemPrice.put(7, 8.99);
        itemPrice.put(8, 3.20);
        itemPrice.put(9, 5.95);
        itemPrice.put(10, 69.0);
    }
}
