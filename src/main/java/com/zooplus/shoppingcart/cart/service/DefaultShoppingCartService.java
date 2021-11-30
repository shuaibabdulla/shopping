package com.zooplus.shoppingcart.cart.service;

import com.zooplus.shoppingcart.cart.models.ShoppingCart;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.tax.service.BasicSalesTaxService;
import com.zooplus.shoppingcart.tax.service.DefaultTaxService;
import com.zooplus.shoppingcart.tax.service.ImportSalesTaxService;
import com.zooplus.shoppingcart.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultShoppingCartService implements ShoppingCartService {

    @Autowired
    @Qualifier("defaultTaxService")
    private TaxService taxService;

    @Override
    public ShoppingCart proceedToCheckout(List<Item> goodsList) {
        if (goodsList == null) {
            return null;
        }
        ShoppingCart cart = getShoppingCart(goodsList);
        return cart;
    }

    private ShoppingCart getShoppingCart(List<Item> goodsList) {
        populateItemTax(goodsList);
        BigDecimal itemSum = getItemSum(goodsList);
        BigDecimal taxSum = getTaxSum(goodsList);
        BigDecimal finalAmount = itemSum.add(taxSum);
        return ShoppingCart.builder()
                .itemList(goodsList)
                .totalItemAmount(itemSum)
                .salesTaxAmount(taxSum)
                .finalAmount(finalAmount)
                .build();
    }

    private BigDecimal getTaxSum(List<Item> goodsList) {
        return goodsList.stream()
                    .map(x -> x.getTaxAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getItemSum(List<Item> goodsList) {
        return goodsList.stream()
                    .map(x -> x.getPrice())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void populateItemTax(List<Item> goodsList) {
        for (Item item : goodsList) {
            item.setTaxAmount(taxService.fetchTaxAmount(item));
        }
    }
}
