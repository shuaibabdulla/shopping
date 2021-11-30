package com.zooplus.shoppingcart.goods.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int itemId;
    private String itemName;
    private String itemDescription;
    private BigDecimal price;
    private ItemType itemType;
    private Category category;
    private BigDecimal taxAmount;


}
