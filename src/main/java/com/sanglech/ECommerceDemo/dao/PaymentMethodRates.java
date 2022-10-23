package com.sanglech.ECommerceDemo.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodRates {
    private double lowRate;
    private double highRate;
    private double pointRate;
}
