package com.sanglech.ECommerceDemo.service;

import com.sanglech.ECommerceDemo.dao.FinalPriceResponse;
import com.sanglech.ECommerceDemo.dao.SaleDAO;
import com.sanglech.ECommerceDemo.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sanglech.ECommerceDemo.constants.Constants.paymentMethodPointsMap;

@Component
public class ECommerceService {

    private final HashMap<String, SaleDAO> history = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    public String getHello() {
        return "Hello World";
    }

    public FinalPriceResponse getFinalPrice(String price, Float priceModeifer, String paymentMethod, String dateTime){
        FinalPriceResponse response = new FinalPriceResponse();
        paymentMethod = paymentMethod.toUpperCase();
        float finalPrice;
        double pointMultiplier;
        try {
            finalPrice = Float.parseFloat(price) * priceModeifer;
            if(paymentMethodPointsMap.containsKey(paymentMethod)){
                pointMultiplier = paymentMethodPointsMap.get(paymentMethod);
            } else {
                throw new InvalidInputException("Invalid Payment Method. Please select a valid payment method.");
            }
            int pointVal = (int) (pointMultiplier * Float.parseFloat(price));
            response.setFinalPrice(String.valueOf(finalPrice));
            response.setPoints(pointVal);

            LocalDateTime requestDateTime = LocalDateTime.parse(dateTime, formatter);
            addToHistory(requestDateTime, finalPrice, pointVal);
            return response;
        }
        catch(NumberFormatException ex){
            throw new InvalidInputException("Invalid Price Input.");
        } catch (DateTimeParseException ex){
            throw new InvalidInputException("Invalid Date Input.");
        } catch(Exception ex){
            throw new InvalidInputException("Invalid Payment Method.");
        }
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

    private void addToHistory(LocalDateTime dateTime, float finalPrice, int pointVal) {

        String requestHourDate = dateTime.getYear() + "-"
                + String.format("%02d",dateTime.getMonthValue()) + "-"
                + String.format("%02d",dateTime.getDayOfMonth())
                + "T"
                + String.format("%02d",dateTime.getHour()) +
                ":00:00Z";


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
