package com.wx.server.controller;

import com.wx.server.service.DataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DataController {

    @GetMapping("/data")
    public List<String> getData() {
        DataService dataService = DataService.getInstance();

        return dataService.getData();
    }

    @GetMapping("/temperature")
    public int getTemperature() {
        DataService dataService = DataService.getInstance();
        return dataService.getTemperature();
    }

    @GetMapping("/imagepath")
    public String getImagePath() {
        DataService dataService = DataService.getInstance();
        return dataService.getImagePath();
    }

    @GetMapping("/fireinfo")
    public String getFireInfo() {
        DataService dataService = DataService.getInstance();
        return dataService.getFireInfo();
    }
}
