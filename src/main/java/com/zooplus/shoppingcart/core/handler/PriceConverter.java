package com.zooplus.shoppingcart.core.handler;

import com.zooplus.shoppingcart.core.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class PriceConverter {

    private final static double ROUNDUP_FACTOR = 0.05;

    public BigDecimal getPriceConverted(BigDecimal price,Integer percentage){
        if(price == null){
            return null;
        }
        return CommonUtil.getBigDecimal(roundOff(getPercentageValue(price, percentage)));
    }

    private double getPercentageValue(BigDecimal price, Integer percentage) {
        return price.multiply(new BigDecimal(percentage))
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.CEILING).doubleValue();
    }

    private static double roundOff(double value)
    {
        return Math.ceil(value / ROUNDUP_FACTOR) * ROUNDUP_FACTOR;
    }
}
