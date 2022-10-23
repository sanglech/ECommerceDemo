package com.sanglech.ECommerceDemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalPriceResponse {
    private String finalPrice;
    private Integer points;
}
