package com.sanglech.ECommerceDemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDAO {
    private String dateTime;
    private String sales;
    private Integer points;
}
