package com.zooplus.shoppingcart.core.handler;

import com.zooplus.shoppingcart.core.logger.ConsoleLogger;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import com.zooplus.shoppingcart.goods.models.Category;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ItemListConverter {

    public static final String SPACE = " ";
    private static final Map<Category, List<String>> categoryStatic = new HashMap<>();
    public static final String IMPORTED = "imported";

    static {
        categoryStatic.put(Category.BOOK, new ArrayList<>(Arrays.asList("book")));
        categoryStatic.put(Category.ENTERTAINMENT, new ArrayList<>(Arrays.asList("music")));
        categoryStatic.put(Category.FOOD, new ArrayList<>(Arrays.asList("chocolate")));
        categoryStatic.put(Category.FRAGRANCE, new ArrayList<>(Arrays.asList("perfume")));
        categoryStatic.put(Category.MEDICAL, new ArrayList<>(Arrays.asList("pills")));
    }

    public List<Item> getCartItemLIst(List<String> inputRequest) {
        if (inputRequest == null) {
            return null;
        }
        List<Item> itemList = inputRequest.stream()
                .map(inputData -> getItem(inputData))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return itemList;
    }


    private List<Item> getItem(String inputData) {
        List<Item> itemList = new ArrayList<>();
        String[] words = inputData.split(SPACE);

        int quantity = Integer.parseInt(words[0]);
        for (int i = 0; i < quantity; i++) {
            populateItem(itemList, words);
        }

        return itemList;
    }

    private void populateItem(List<Item> itemList, String[] words) {
        if (isValidRequest(words.length, 4)) {
            ConsoleLogger.console("THE given input is not valid :: " + words.toString());
            return;
        }
        String itemDesc = getItemDescription(words, 2);
        String itemName = getItemDescription(words, 3);
        BigDecimal price = CommonUtil.getBigDecimal(Double.parseDouble(words[words.length - 1]));
        if (validateInputRequest(itemName, itemDesc, price)) {
            Item item = Item.builder()
                    .itemName(itemName)
                    .itemDescription(itemDesc)
                    .price(price)
                    .itemType(getItemType(itemDesc))
                    .category(getCategory(itemDesc))
                    .build();
            itemList.add(item);
        } else {
            ConsoleLogger.console("THE given input is not valid :: " + words);
        }
    }

    private boolean isValidRequest(int length, int i) {
        return length < i;
    }

    private String getItemDescription(String[] words, int i) {
        return Arrays.stream(words)
                .skip(1)
                .limit(words.length - i)
                .collect(Collectors.joining(" "));
    }

    private Category getCategory(String itemDescription) {
        Optional<Category> category = categoryStatic.entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(s -> itemDescription.contains(s)))
                .map(Map.Entry::getKey)
                .findFirst();
        return category.orElse(Category.OTHERS);
    }

    private ItemType getItemType(String itemDescription) {
        if (itemDescription == null) {
            return null;
        }
        if (itemDescription.contains(IMPORTED)) {
            return ItemType.IMPORTED;
        }
        return ItemType.NON_IMPORTED;
    }

    private boolean validateInputRequest(String itemName, String itemDesc, BigDecimal price) {
        if (!StringUtils.hasLength(itemName)) {
            return false;
        }
        if (!StringUtils.hasLength(itemDesc)) {
            return false;
        }
        if (null == price) {
            return false;
        }
        return true;
    }
}
