package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.handler.PriceConverter;
import com.zooplus.shoppingcart.goods.models.Category;
import com.zooplus.shoppingcart.goods.models.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasicSalesTaxService implements TaxService{

    private static final List<Category> exemptionList = new ArrayList<>();

    private final PriceConverter priceConverter;

    @Value("${basic.sales.percentage}")
    private String basicPercentage;

    static {
        exemptionList.add(Category.BOOK);
        exemptionList.add(Category.FOOD);
        exemptionList.add(Category.MEDICAL);
    }

    public BasicSalesTaxService(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }


    @Override
    public BigDecimal fetchTaxAmount(Item item) {
        BigDecimal taxAmount = new BigDecimal(0);
        if(isItemExemptedForTax(item)){
            return taxAmount;
        }
        taxAmount = priceConverter.getPriceConverted(item.getPrice(),Integer.valueOf(basicPercentage));
        return taxAmount;
    }

    private boolean isItemExemptedForTax(Item item) {
        return exemptionList.contains(item.getCategory());
    }


}
