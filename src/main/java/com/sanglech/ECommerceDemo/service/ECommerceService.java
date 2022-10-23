package com.sanglech.ECommerceDemo.service;

import com.sanglech.ECommerceDemo.FinalPriceResponse;
import org.springframework.stereotype.Component;

@Component
public class ECommerceService {

    public String getHello() {
        return "Hello World";
    }

    public FinalPriceResponse getFinalPrice(String price){
        return new FinalPriceResponse(price,50);
    }
}
