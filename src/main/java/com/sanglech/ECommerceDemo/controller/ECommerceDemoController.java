package com.sanglech.ECommerceDemo.controller;

import com.sanglech.ECommerceDemo.service.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ECommerceDemoController {

    @Autowired
    private final ECommerceService eCommerceService;

    public ECommerceDemoController (ECommerceService service){
        this.eCommerceService = service;
    }

    @QueryMapping
    public String getHello(){
        return this.eCommerceService.getHello();
    }
}