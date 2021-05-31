package com.example.demo.Controller;

import com.example.demo.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;
    @GetMapping
    public void getData(){
        dataService.getData();
    }
}
