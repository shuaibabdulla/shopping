package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.goods.models.Item;

import java.math.BigDecimal;

public interface TaxService {

    BigDecimal fetchTaxAmount(Item item);

}
