//package by.clevertec.ivanchenko.controller;
//
//import by.clevertec.ivanchenko.model.Item;
//import by.clevertec.ivanchenko.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/item")
//public class ItemController {
//
//    private final ItemService itemService;
//
//    @Autowired
//    public ItemController(ItemService itemService) {
//        this.itemService = itemService;
//    }
//
//    @GetMapping()
//    public Map<Integer,Item> getItem(@RequestParam("id") List<Integer> idList,
//                                     @RequestParam("qty") List<Integer> qtyList) {
//        int i = 0;
//        Map<Integer,Item> items = new LinkedHashMap<>();
//        for (Integer id : idList) {
//            items.put(qtyList.get(i++), itemService.findOne(id));
//        }
//        return items;
//    }
//}
