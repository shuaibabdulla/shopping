package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.handler.PriceConverter;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ImportSalesTaxService implements TaxService{

    @Value("${import.sales.percentage}")
    private String importSalesPercentage;

    private final PriceConverter priceConverter;

    public ImportSalesTaxService(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    @Override
    public BigDecimal fetchTaxAmount(Item item) {
        BigDecimal taxAmount = new BigDecimal(0);
        if(!isItemImported(item)){
            return taxAmount;
        }
        taxAmount = priceConverter.getPriceConverted(item.getPrice(),Integer.valueOf(importSalesPercentage));
        return taxAmount;
    }

    private boolean isItemImported(Item item) {
        return item.getItemType().equals(ItemType.IMPORTED);
    }
}
