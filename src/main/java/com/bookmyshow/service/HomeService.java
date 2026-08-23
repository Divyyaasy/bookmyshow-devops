package com.bookmyshow.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getStatus() {
        return "BookMyShow Service is Running Successfully!";
    }
}