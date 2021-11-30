package com.zooplus.shoppingcart.core.handler;

import com.zooplus.shoppingcart.cart.models.ShoppingCart;
import com.zooplus.shoppingcart.cart.service.ShoppingCartService;
import com.zooplus.shoppingcart.core.logger.ConsoleLogger;
import com.zooplus.shoppingcart.goods.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ShoppingCartHandler {

    public static final String INPUT = "Input";
    public static final String OUTPUT = "OUTPUT";
    public static final String ONE = "1 ";
    public static final String SALES_TAX = "Sales Tax : ";
    public static final String TOTAL = "Total : ";
    private final ItemListConverter itemListConverter;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartHandler(ItemListConverter itemListConverter, ShoppingCartService shoppingCartService) {
        this.itemListConverter = itemListConverter;
        this.shoppingCartService = shoppingCartService;
    }

    public void processItemsRequest(List<String> inputRequest){
        if(CollectionUtils.isEmpty(inputRequest)){
            ConsoleLogger.console("The input request is invalid");
            return;
        }
        printInputRequest(inputRequest);
        List<Item> itemList = itemListConverter.getCartItemLIst(inputRequest);
        ShoppingCart shoppingCart = shoppingCartService.proceedToCheckout(itemList);
        printOutputResponse(shoppingCart);
    }

    private void printInputRequest(List<String> inputRequest){
        ConsoleLogger.console(INPUT);
        for(String input:inputRequest){
            ConsoleLogger.console(input);
        }
    }

    private void printOutputResponse(ShoppingCart shoppingCart){
        ConsoleLogger.console(OUTPUT);
        for(Item item: shoppingCart.getItemList()){
            ConsoleLogger.console(ONE + item.getItemDescription() + " " + item.getPrice().add(item.getTaxAmount()));
        }
        ConsoleLogger.console(SALES_TAX + shoppingCart.getSalesTaxAmount());
        ConsoleLogger.console(TOTAL + shoppingCart.getFinalAmount());
    }

}
