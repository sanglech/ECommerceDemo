package com.sanglech.ECommerceDemo.constants;

import com.sanglech.ECommerceDemo.dao.PaymentMethodRates;
import java.util.HashMap;

public class Constants {
    public static final HashMap<String, PaymentMethodRates> paymentMethodPointsMap= new HashMap<>();
    
    static{
        PaymentMethodRates CASH = new PaymentMethodRates(0.9,1.0,0.05);
        PaymentMethodRates CASH_ON_DELIVERY = new PaymentMethodRates(1,1.02,0.05);
        PaymentMethodRates VISA = new PaymentMethodRates(0.95,1.0,0.03);
        PaymentMethodRates MASTERCARD = new PaymentMethodRates(0.95,1.0,0.03);
        PaymentMethodRates AMEX = new PaymentMethodRates(0.98,1.01,0.02);
        PaymentMethodRates JCB = new PaymentMethodRates(0.95,1.0,0.05);

        paymentMethodPointsMap.put("CASH",CASH);
        paymentMethodPointsMap.put("CASH_ON_DELIVERY",CASH_ON_DELIVERY);
        paymentMethodPointsMap.put("VISA",VISA);
        paymentMethodPointsMap.put("MASTERCARD",MASTERCARD);
        paymentMethodPointsMap.put("AMEX",AMEX);
        paymentMethodPointsMap.put("JCB",JCB);
    }
}
