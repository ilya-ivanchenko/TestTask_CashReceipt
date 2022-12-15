package by.clevertec.ivanchenko;

import by.clevertec.ivanchenko.model.Item;

public class Receipt {

    public static void main(String[] args) {
        double discountCard = 1.0;
        double discountItem;
        double totalAll = 0.0;
        String delimeter = "-------------------------------------------------------";
        Item item = new Item();
        item.createItemList();
        item.createItemPrice();

        System.out.printf("%30s \n%s \n%-5s %-30s %7s %10s\n", "CASH RECEIPT", delimeter, "QTY", "DESCRIPTION", "PRICE", "TOTAL");

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
                        System.err.println("\nItem with id = " + array[0] + " wasn't found!");
                        break; // exp
                    }
                    if (Integer.parseInt(quantity) > 5) {
                        discountItem = 0.9;
                    }
                    double countPrice = item.getItemPrice().get(Integer.valueOf(array[0])) * discountCard * discountItem;
                    double countTotal = Integer.parseInt(quantity) * countPrice;
                    totalAll+=countTotal;
                    String price = String.format("$%.2f", countPrice);
                    String total = String.format("$%.2f", countTotal);
                    System.out.printf("\n %-4s %-30s %7s %10s",
                            quantity, currentItem, price, total);
                } else if (!(args[args.length - 1]).matches("card-\\d+")){
                    System.out.println("\nRequest must include numbers: number(id) - number(quantity) and optionally discount: 'card'-number");
                    break;
                }
            } else {
                System.err.println("\nIncorrect request!");
                System.out.println("Request must be: id-quantity + optionally discount: 'card'-number");
            }
        }
        String totalPrice = String.format("$%.2f", totalAll);
        System.out.println("\n" + delimeter);
        System.out.printf("%-40s %14s","TOTAL", totalPrice);
    }
}


// TO DO:

// items,price in the code?
// Exceptions
// Patterns (Factory, Builder, Decorator)
// FileWriter
// README
// FileReader
// Unit tests
// *PostgreSQL
// *RESTFUL
// *DOCKER
// *Add, improve smth
