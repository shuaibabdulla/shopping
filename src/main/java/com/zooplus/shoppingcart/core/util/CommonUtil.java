package com.zooplus.shoppingcart.core.util;

import com.zooplus.shoppingcart.core.logger.ConsoleLogger;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtil {

    public static BigDecimal getBigDecimal(double value){
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBigDecimal(String value){
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public static List<String> getFileData(String fileName) throws IOException {
        try(InputStream stream = CommonUtil.class.getResourceAsStream( fileName );
            InputStreamReader inputStreamReader = new InputStreamReader( stream );
            BufferedReader br = new BufferedReader( inputStreamReader ) )
        {
           return br.lines().collect(Collectors.toList());
        }
    }
}
