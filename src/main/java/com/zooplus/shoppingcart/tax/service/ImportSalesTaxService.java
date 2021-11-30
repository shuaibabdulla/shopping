package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.handler.PriceConverter;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ImportSalesTaxService implements TaxService{

    private static final int importSalesPercentage = 5;
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
        taxAmount = priceConverter.getPriceConverted(item.getPrice(),importSalesPercentage);
        return taxAmount;
    }

    private boolean isItemImported(Item item) {
        return item.getItemType().equals(ItemType.IMPORTED);
    }
}
