package com.zooplus.shoppingcart.cart.service;

import com.zooplus.shoppingcart.cart.models.ShoppingCart;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import com.zooplus.shoppingcart.goods.models.Category;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.goods.models.ItemType;
import com.zooplus.shoppingcart.tax.service.TaxService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DefaultShoppingCartServiceTest {

   @InjectMocks
   DefaultShoppingCartService defaultShoppingCartService;

   @Mock
   TaxService taxService;

   @Test
   public void whenInvalidRequest_thenReturnNullValue(){
       List<Item> itemList = null;
       Assert.assertNull(defaultShoppingCartService.proceedToCheckout(itemList));
   }

    @Test
    public void whenValidGoodsListImportAndNonImport_thenReturnValidShoppingCart(){
        List<Item> itemList = getMockItemList();
        Mockito.when(taxService.fetchTaxAmount(itemList.get(0)))
                .thenReturn(CommonUtil.getBigDecimal(4.2));
        Mockito.when(taxService.fetchTaxAmount(itemList.get(1)))
                .thenReturn(CommonUtil.getBigDecimal(1.9));
        Mockito.when(taxService.fetchTaxAmount(itemList.get(2)))
                .thenReturn(CommonUtil.getBigDecimal(0));
        Mockito.when(taxService.fetchTaxAmount(itemList.get(3)))
                .thenReturn(CommonUtil.getBigDecimal(0.6));
        ShoppingCart cart = defaultShoppingCartService.proceedToCheckout(itemList);
        BigDecimal expectedFinalAmount = CommonUtil.getBigDecimal("74.68");
        BigDecimal expectedSalesAmount = CommonUtil.getBigDecimal("6.70");
        Assert.assertNotNull(cart);
        Assert.assertEquals("The final amount is not equal",expectedFinalAmount,cart.getFinalAmount());
        Assert.assertEquals("The Tax amount is not equal",expectedSalesAmount,cart.getSalesTaxAmount());
    }

    private List<Item> getMockItemList() {
        List<Item> itemList = new ArrayList<>();
        Item itemOne = Item.builder()
                .itemName("perfume")
                .price(CommonUtil.getBigDecimal("27.99"))
                .category(Category.FRAGRANCE)
                .itemType(ItemType.IMPORTED)
                .build();
        Item itemTwo = Item.builder()
                .itemName("perfume")
                .price(CommonUtil.getBigDecimal("18.99"))
                .category(Category.FRAGRANCE)
                .itemType(ItemType.NON_IMPORTED)
                .build();
        Item itemThree = Item.builder()
                .itemName("pills")
                .price(CommonUtil.getBigDecimal("9.75"))
                .category(Category.MEDICAL)
                .itemType(ItemType.NON_IMPORTED)
                .build();
        Item itemFour = Item.builder()
                .itemName("chocolates")
                .price(CommonUtil.getBigDecimal("11.25"))
                .category(Category.FOOD)
                .itemType(ItemType.IMPORTED)
                .build();
        itemList.add(itemOne);
        itemList.add(itemTwo);
        itemList.add(itemThree);
        itemList.add(itemFour);
        return itemList;
    }

}
