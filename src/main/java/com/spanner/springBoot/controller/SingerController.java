package com.spanner.springBoot.controller;

import com.spanner.springBoot.service.SingerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.spanner.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Api(tags = {"Med Orders Search APIs"})
@Slf4j
@RequiredArgsConstructor
public class SingerController {
    @Autowired
    SingerService tableService;

    @GetMapping(value = "/")
    public List<Singers> hello(){
        tableService.saveTrader();
        return tableService.saveTrader();
    }
}