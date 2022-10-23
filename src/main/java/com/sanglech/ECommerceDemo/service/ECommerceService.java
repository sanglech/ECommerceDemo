package com.sanglech.ECommerceDemo.service;

import com.sanglech.ECommerceDemo.FinalPriceResponse;
import com.sanglech.ECommerceDemo.exception.InvalidInputException;
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
        double pointMultiplier;

        if(paymentMethodPointsMap.containsKey(paymentMethod)){
            pointMultiplier = paymentMethodPointsMap.get(paymentMethod);
        } else {
           throw new InvalidInputException("Invalid Payment Method. Please select a valid payment method.");
        }

        int pointVal = (int) (pointMultiplier * Float.parseFloat(price));
        response.setFinalPrice(String.valueOf(finalPrice));
        response.setPoints(pointVal);

        return response;
    }
}
