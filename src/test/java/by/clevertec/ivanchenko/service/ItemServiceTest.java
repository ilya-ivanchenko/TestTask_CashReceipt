package by.clevertec.ivanchenko.service;

import by.clevertec.ivanchenko.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void findOneIsWork() {
        Item item = new Item();
        item.setId(1);

        assertEquals("Shampoo 500ml", itemService.findOne(item.getId()).getName());
        assertEquals(3.95, itemService.findOne(item.getId()).getPrice());
    }
}