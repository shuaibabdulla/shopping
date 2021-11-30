package com.zooplus.shoppingcart.cart.service;

import com.zooplus.shoppingcart.cart.models.ShoppingCart;
import com.zooplus.shoppingcart.goods.models.Item;

import java.util.List;

public interface ShoppingCartService {

    /**
     * This method is used to return the Shopping cart with list of items and its final amount with sales tax.
     * @param goodsList the list of items that contain al the properties of item
     * @return Shopping cart shuold contain statistics of cart.
     */
    ShoppingCart proceedToCheckout(List<Item> goodsList);
}
