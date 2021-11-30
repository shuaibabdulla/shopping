package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.util.CommonUtil;
import com.zooplus.shoppingcart.goods.models.Category;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTaxServiceTest {

    @InjectMocks
    DefaultTaxService defaultTaxService;

    @Mock
    BasicSalesTaxService basicSalesTaxService;

    @Mock
    ImportSalesTaxService importSalesTaxService;

    @Test
    public void whenItemRequestIsNotExemptionImported_thenReturnValidSalesTax(){
        Item item = Item.builder()
                .itemName("perfume")
                .price(CommonUtil.getBigDecimal(Double.parseDouble("47.50")))
                .category(Category.OTHERS)
                .itemType(ItemType.IMPORTED)
                .build();
        BigDecimal expected = CommonUtil.getBigDecimal(Double.parseDouble("7.15"));
        Mockito.when(basicSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(4.75));
        Mockito.when(importSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(2.4));
        Assert.assertEquals("The import sales tax should be zero",expected,defaultTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenItemRequestIsExemptionImported_thenReturnValidSalesTax(){
        Item item = Item.builder()
                .itemName("perfume")
                .price(CommonUtil.getBigDecimal(Double.parseDouble("47.50")))
                .category(Category.BOOK)
                .itemType(ItemType.IMPORTED)
                .build();
        BigDecimal expected = CommonUtil.getBigDecimal(Double.parseDouble("2.4"));
        Mockito.when(basicSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(0.00));
        Mockito.when(importSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(2.4));
        Assert.assertEquals("The import sales tax should be zero",expected,defaultTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenItemRequestIsExemptionNonImported_thenReturnValidSalesTax(){
        Item item = Item.builder()
                .itemName("perfume")
                .price(CommonUtil.getBigDecimal(Double.parseDouble("47.50")))
                .category(Category.BOOK)
                .itemType(ItemType.NON_IMPORTED)
                .build();
        BigDecimal expected = CommonUtil.getBigDecimal(Double.parseDouble("0.00"));
        Mockito.when(basicSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(0.00));
        Mockito.when(importSalesTaxService.fetchTaxAmount(item))
                .thenReturn(CommonUtil.getBigDecimal(0.00));
        Assert.assertEquals("The import sales tax should be zero",expected,defaultTaxService.fetchTaxAmount(item));
    }
}
