package by.clevertec.ivanchenko.service;

import by.clevertec.ivanchenko.model.Item;
import by.clevertec.ivanchenko.repository.ItemRepository;
import by.clevertec.ivanchenko.util.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item findOne(int id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(ItemNotFoundException::new);
    }
}
