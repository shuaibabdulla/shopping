package com.zooplus.shoppingcart.tax.service;

import com.zooplus.shoppingcart.core.handler.PriceConverter;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ImportSalesTaxServiceTest {

    @InjectMocks
    ImportSalesTaxService importSalesTaxService;

    @Mock
    PriceConverter priceConverter;

    @Before
    public void init(){
        ReflectionTestUtils.setField(importSalesTaxService, "importSalesPercentage", "5");
    }

    @Test
    public void whenRequestItemIsNonImported_thenReturnZeroImportSalesTaxAmount(){
        Item item = new Item();
        item.setItemName("book");
        item.setItemType(ItemType.NON_IMPORTED);
        item.setPrice(new BigDecimal(10.00));
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals("The import sales tax should be zero", expected,importSalesTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenRequestItemIsOther_thenReturnZeroImportSalesTaxAmount(){
        Item item = new Item();
        item.setItemName("book");
        item.setItemType(ItemType.OTHER);
        item.setPrice(new BigDecimal(10.00));
        BigDecimal expected = new BigDecimal(0);
        Assert.assertEquals("The import sales tax should be zero", expected,importSalesTaxService.fetchTaxAmount(item));
    }

    @Test
    public void whenRequestItemIsImportedAndNonExempted_thenReturnValidImportSalesTaxAmount(){
        Item item = new Item();
        item.setItemName("Music CD");
        item.setItemType(ItemType.IMPORTED);
        item.setPrice(new BigDecimal(10.00));
        BigDecimal expected = CommonUtil.getBigDecimal(.5);
        Mockito.when(priceConverter.getPriceConverted(item.getPrice(),5))
                .thenReturn(CommonUtil.getBigDecimal(0.5));
        Assert.assertEquals("The import sales tax should be 0.05",
                expected,importSalesTaxService.fetchTaxAmount(item));
    }

}
