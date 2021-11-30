package com.zooplus.shoppingcart;

import com.zooplus.shoppingcart.core.handler.ShoppingCartHandler;
import com.zooplus.shoppingcart.core.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ShoppingCartApplication {

    @Autowired
    private ShoppingCartHandler shoppingCartHandler;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @PostConstruct
    public void listen() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter fileName : Options is 1 . input1.txt 2. input2.txt 3. input3.txt ");
        String fileName = myObj.nextLine();  // Read user input
        System.out.println("file name  is: " + fileName);
        List<String> fileInputRequest;
        fileInputRequest = CommonUtil.getFileInputData(fileName);
        shoppingCartHandler.processItemsRequest(fileInputRequest);
    }


}
