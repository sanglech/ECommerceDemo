package com.sanglech.ECommerceDemo.service;

import com.sanglech.ECommerceDemo.FinalPriceResponse;
import com.sanglech.ECommerceDemo.SaleDAO;
import com.sanglech.ECommerceDemo.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sanglech.ECommerceDemo.constants.Constants.paymentMethodPointsMap;

@Component
public class ECommerceService {

    private final HashMap<String, SaleDAO> history = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
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

        addToHistory(dateTime, finalPrice, pointVal);

        return response;
    }

    public List<SaleDAO> getSalesHistory(String startDate, String endDate ) {
        List<SaleDAO> response = new ArrayList<>();
        LocalDateTime start = LocalDateTime.parse(startDate,formatter);
        LocalDateTime end = LocalDateTime.parse(endDate,formatter);

        for(String date: history.keySet()){
            LocalDateTime temp = LocalDateTime.parse(date,formatter);
            if((temp.isBefore(end) && temp.isAfter(start))|| temp.isEqual(start) || temp.isEqual(end)){
                response.add(history.get(date));
            }
        }
        return response;
    }

    private void addToHistory(String dateTime, float finalPrice, int pointVal) {
        LocalDateTime requestDateTime = LocalDateTime.parse(dateTime, formatter);
        String requestHourDate = requestDateTime.getYear() + "-"
                + requestDateTime.getMonthValue() + "-"
                + requestDateTime.getDayOfMonth() + "-"
                + "T"
                + requestDateTime.getHour() +
                "00:00";


        if(history.containsKey(requestHourDate)){
            double sales = Double.parseDouble(history.get(requestHourDate).getSales()) + finalPrice;

            SaleDAO updatedVal = history.get(requestHourDate);
            updatedVal.setPoints(updatedVal.getPoints()+pointVal);
            updatedVal.setSales(String.valueOf(sales));

            history.put(requestHourDate,updatedVal);
        } else {
            history.put(requestHourDate,new SaleDAO(requestHourDate,String.valueOf(finalPrice), pointVal));
        }
    }

}
