package com.zooplus.shoppingcart.cart.models;

import com.zooplus.shoppingcart.goods.models.Item;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ShoppingCart {

    private List<Item> itemList;
    private BigDecimal salesTaxAmount;
    private BigDecimal totalItemAmount;
    private BigDecimal finalAmount;

}
