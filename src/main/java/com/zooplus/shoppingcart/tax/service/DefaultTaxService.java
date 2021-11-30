package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.goods.models.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class DefaultTaxService implements TaxService{

    private final BasicSalesTaxService basicSalesTaxService;
    private final ImportSalesTaxService importSalesTaxService;

    public DefaultTaxService(BasicSalesTaxService basicSalesTaxService, ImportSalesTaxService importSalesTaxService) {
        this.basicSalesTaxService = basicSalesTaxService;
        this.importSalesTaxService = importSalesTaxService;
    }

    @Override
    public BigDecimal fetchTaxAmount(Item item) {
        BigDecimal basicTax = basicSalesTaxService.fetchTaxAmount(item);
        BigDecimal importTax = importSalesTaxService.fetchTaxAmount(item);
        return basicTax.add(importTax);
    }

}
