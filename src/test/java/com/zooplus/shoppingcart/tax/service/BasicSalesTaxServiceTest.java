package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.handler.PriceConverter;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import com.zooplus.shoppingcart.goods.models.Category;
import com.zooplus.shoppingcart.goods.models.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class BasicSalesTaxServiceTest {

    @InjectMocks
    BasicSalesTaxService basicSalesTaxService;

    @Mock
    PriceConverter priceConverter;

    @Test
    public void whenItemRequestIsExemptionBook_thenReturnZero(){
        Item item = new Item();
        item.setItemName("book");
        item.setCategory(Category.BOOK);
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals("The basic sales tax should be zero",expected,basicSalesTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenItemRequestIsExemptionFood_thenReturnZero(){
        Item item = new Item();
        item.setItemName("choclate");
        item.setCategory(Category.FOOD);
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals("The basic sales tax should be zero",expected,basicSalesTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenItemRequestIsExemptionMedical_thenReturnZero(){
        Item item = new Item();
        item.setItemName("pills");
        item.setCategory(Category.MEDICAL);
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals("The basic sales tax should be zero",expected,basicSalesTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenItemRequestIsNotExemption_thenReturnValidSalesTax(){
        Item item = new Item();
        item.setItemName("Music cd");
        item.setCategory(Category.ENTERTAINMENT);
        item.setPrice(CommonUtil.getBigDecimal(14.99));
        BigDecimal expected = CommonUtil.getBigDecimal(1.5);
        Mockito.when(priceConverter.getPriceConverted(item.getPrice(),10))
                .thenReturn(CommonUtil.getBigDecimal(1.5));
        Assert.assertEquals("The basic sales tax should not be equal",expected,basicSalesTaxService.fetchTaxAmount(item));
    }
}
