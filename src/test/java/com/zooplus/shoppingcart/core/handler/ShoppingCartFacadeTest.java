package com.zooplus.shoppingcart.core.handler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFacadeTest {



    @Test
    public void whenValidNonImportGoodList_thenReturnValidShoppingCart(){
        List<String> inputRequest = new ArrayList<>();
        inputRequest.add("1 book at 12.49");
        inputRequest.add("1 music CD at 14.99");
        inputRequest.add("1 chocolate bar at 0.85");
    }

}
