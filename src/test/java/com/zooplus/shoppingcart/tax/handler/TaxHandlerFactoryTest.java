package com.zooplus.shoppingcart.tax.handler;

import com.zooplus.shoppingcart.core.exception.InvalidRequestException;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import com.zooplus.shoppingcart.tax.service.BasicSalesTaxService;
import com.zooplus.shoppingcart.tax.service.ImportSalesTaxService;
import com.zooplus.shoppingcart.tax.service.TaxService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaxHandlerFactoryTest {

    @InjectMocks
    TaxHandlerFactory taxHandlerFactory;

    @Mock
    BasicSalesTaxService basicSalesTaxService;

    @Mock
    ImportSalesTaxService importSalesTaxService;


    @Test
    public void whenRequestIsInvalid_thenExpectException() throws InvalidRequestException {
        Item item = new Item();
        item.setItemType(ItemType.OTHER);
        try {
            taxHandlerFactory.getTaxHandler(item);
            Assert.fail("IllegalArgumentException not thrown");
        } catch (InvalidRequestException ex){
        }
    }

    @Test
    public void whenRequestIsValidBasic_thenExpectBasicSalesService() throws InvalidRequestException {
        Item item = new Item();
        item.setItemType(ItemType.NON_IMPORTED);
        TaxService taxService = taxHandlerFactory.getTaxHandler(item);
        Assert.assertTrue(taxService instanceof BasicSalesTaxService);
    }

    @Test
    public void whenRequestIsValidImportedItem_thenExpectImportedSalesService() throws InvalidRequestException {
        Item item = new Item();
        item.setItemType(ItemType.IMPORTED);
        TaxService taxService = taxHandlerFactory.getTaxHandler(item);
        Assert.assertTrue(taxService instanceof ImportSalesTaxService);
    }
}
