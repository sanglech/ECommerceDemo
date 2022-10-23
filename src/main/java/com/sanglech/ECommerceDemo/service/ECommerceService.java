package com.sanglech.ECommerceDemo.service;

import com.sanglech.ECommerceDemo.FinalPriceResponse;
import org.springframework.stereotype.Component;

import static com.sanglech.ECommerceDemo.constants.Constants.paymentMethodPointsMap;

@Component
public class ECommerceService {

    public String getHello() {
        return "Hello World";
    }

    public FinalPriceResponse getFinalPrice(String price, Float priceModeifer, String paymentMethod, String dateTime){
        FinalPriceResponse response = new FinalPriceResponse();
        float finalPrice = Float.parseFloat(price) * priceModeifer;

        // TODO Handle invalid mapping
        int pointVal = (int) (paymentMethodPointsMap.get(paymentMethod) * Float.parseFloat(price));
        response.setFinalPrice(String.valueOf(finalPrice));
        response.setPoints(pointVal);

        return response;
    }
}
