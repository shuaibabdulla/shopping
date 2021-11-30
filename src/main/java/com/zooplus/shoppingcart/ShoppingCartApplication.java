package com.zooplus.shoppingcart;

import com.zooplus.shoppingcart.core.handler.ShoppingCartFacade;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ShoppingCartApplication {

    @Autowired
    private ShoppingCartFacade shoppingCartFacade;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @PostConstruct
    public void listen() {
        List<String> fileInputRequest = null;
        try {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter fileName : Options is 1 . input1.txt 2. input2.txt 3. input3.txt ");
            String fileName = myObj.nextLine();  // Read user input
            System.out.println("file name  is: " + fileName);
            fileInputRequest = CommonUtil.getFileData("/" + fileName);
            shoppingCartFacade.processItemsRequest(fileInputRequest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
