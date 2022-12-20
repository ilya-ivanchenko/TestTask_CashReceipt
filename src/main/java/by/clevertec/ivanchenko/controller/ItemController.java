package by.clevertec.ivanchenko.controller;

import by.clevertec.ivanchenko.model.Item;
import by.clevertec.ivanchenko.service.ItemService;
import by.clevertec.ivanchenko.service.ReceiptServiceRest;
import by.clevertec.ivanchenko.util.ErrorResponse;
import by.clevertec.ivanchenko.util.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ReceiptServiceRest receiptServiceRest;

    @Autowired
    public ItemController(ItemService itemService, ReceiptServiceRest receiptServiceRest) {
        this.itemService = itemService;
        this.receiptServiceRest = receiptServiceRest;
    }

    @GetMapping()
    @ResponseBody
    public Map<Integer, Item> getItem(@RequestParam("id") List<Integer> idList,
                                      @RequestParam("qty") List<Integer> qtyList,
                                      @RequestParam(value = "card", required = false) Integer card) {
        int i = 0;
        Map<Integer, Item> items = new LinkedHashMap<>();
        for (Integer id : idList) {
            items.put(qtyList.get(i++), itemService.findOne(id));
        }

        receiptServiceRest.calculateReceipt(idList, qtyList, card);                                                        // Creating and printing a receipt
        return items;                                                                                                      // JSON, without discount
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> itemNotFound(ItemNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Incorrect item ID. Item wasn't found!");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
