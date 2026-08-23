package com.bookmyshow.controller;

import com.bookmyshow.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home() {
        return "BookMyShow Backend is Running Successfully!";
    }

    @GetMapping("/api/health")
    public String health() {
        return "BookMyShow Backend Health: UP";
    }

    @GetMapping("/api/status")
    public String status() {
        return homeService.getStatus();
    }
}