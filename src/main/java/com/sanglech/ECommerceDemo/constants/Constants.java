package com.sanglech.ECommerceDemo.constants;

import java.util.HashMap;

public class Constants {
    public static final HashMap<String, Double> paymentMethodPointsMap= new HashMap<>();
    
    static{
        paymentMethodPointsMap.put("CASH",0.05);
        paymentMethodPointsMap.put("CASH_ON_DELIVERY",0.05);
        paymentMethodPointsMap.put("VISA",0.03);
        paymentMethodPointsMap.put("MASTERCARD",0.05);
    }
}
