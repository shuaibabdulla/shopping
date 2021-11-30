package com.zooplus.shoppingcart.core.handler;

import com.zooplus.shoppingcart.goods.models.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemListConverterTest {

    private ItemListConverter itemListConverter = new ItemListConverter();

    @Test
    public void whenInvalidInputRequest_thenReturnNull(){
        List<String> inputRequestList = null;
        Assert.assertNull(itemListConverter.getCartItemLIst(inputRequestList));
    }

    @Test
    public void whenValidInputRequest_thenGetValidItemList(){
        List<String> inputRequest = new ArrayList<>();
        inputRequest.add("1 book at 12.49");
        inputRequest.add("1 music CD at 14.99");
        inputRequest.add("1 chocolate bar at 0.85");
        inputRequest.add("1 imported box of chocolates at 10.00");
        inputRequest.add("1 imported bottle of perfume at 47.50");
        List<Item> items= itemListConverter.getCartItemLIst(inputRequest);
        Assert.assertNotNull(items);
        Assert.assertEquals(5,items.size());
    }

    @Test
    public void whenValidInputRequestWithOneInvalid_thenGetValidItemList(){
        List<String> inputRequest = new ArrayList<>();
        inputRequest.add("1 book at 12.49");
        inputRequest.add("1 music CD at 14.99");
        inputRequest.add("1 chocolate bar at 0.85");
        inputRequest.add("1 10.00");
        inputRequest.add("1 imported bottle of perfume at 47.50");
        List<Item> items= itemListConverter.getCartItemLIst(inputRequest);
        Assert.assertNotNull(items);
        Assert.assertEquals(4,items.size());
    }

}
