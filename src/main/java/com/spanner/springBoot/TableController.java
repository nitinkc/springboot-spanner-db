package com.spanner.springBoot;

import com.google.cloud.spring.data.spanner.core.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
    @Autowired
    TableService tableService;

    @GetMapping(value = "/")
    public String hello(){
        tableService.saveTrader();
        return "Test Successful";
    }
}