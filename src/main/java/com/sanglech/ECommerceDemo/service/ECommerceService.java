package com.sanglech.ECommerceDemo.service;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class ECommerceService implements GraphQLQueryResolver {

    public String getHello() {
        return "Hello World";
    }
}
