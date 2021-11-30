package com.zooplus.shoppingcart.core.util;

import com.zooplus.shoppingcart.core.logger.ConsoleLogger;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

    public static BigDecimal getBigDecimal(double value){
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBigDecimal(String value){
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    public static List<String> getFileInputData(String fileName) {
        List<String> fileInputRequest = new ArrayList<>();
        File file;
        try {
            file = new ClassPathResource(fileName).getFile();
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                fileInputRequest.add(line);
            }
        } catch (IOException exception) {
            ConsoleLogger.console("The given" + fileName + "does not exist");
        }
        return fileInputRequest;
    }
}
