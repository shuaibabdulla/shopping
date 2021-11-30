package com.zooplus.shoppingcart.tax.handler;

import com.zooplus.shoppingcart.core.exception.InvalidRequestException;
import com.zooplus.shoppingcart.goods.models.Item;
import com.zooplus.shoppingcart.tax.service.BasicSalesTaxService;
import com.zooplus.shoppingcart.tax.service.ImportSalesTaxService;
import com.zooplus.shoppingcart.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxHandlerFactory {

    private final  BasicSalesTaxService basicSalesTaxService;
    private final  ImportSalesTaxService importSalesTaxService;

    @Autowired
    public TaxHandlerFactory(BasicSalesTaxService basicSalesTaxService, ImportSalesTaxService importSalesTaxService) {
        this.basicSalesTaxService = basicSalesTaxService;
        this.importSalesTaxService = importSalesTaxService;
    }

    public TaxService getTaxHandler(Item item) throws InvalidRequestException {
        if(item == null){
            throw new InvalidRequestException();
        }
        switch (item.getItemType()){
            case IMPORTED:
                return importSalesTaxService;
            case NON_IMPORTED:
                return basicSalesTaxService;
            default:
                throw new InvalidRequestException();
        }
    }
}
