package com.zooplus.shoppingcart.cart.service;

import com.zooplus.shoppingcart.cart.models.ShoppingCart;
import com.zooplus.shoppingcart.goods.models.Item;

import java.util.List;

public interface ShoppingCartService {

    /**
     * This method is used to return the Shopping cart with list of items and its final amount with sales tax.
     * @param goodsList
     * @return
     */
    ShoppingCart proceedToCheckout(List<Item> goodsList);
}
