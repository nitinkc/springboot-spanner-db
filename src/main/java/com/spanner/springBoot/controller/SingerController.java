package com.spanner.springBoot.controller;

import com.spanner.springBoot.dto.SingersDto;
import com.spanner.springBoot.service.SingerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.spanner.springBoot.model.spanner.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SingerController {
    @Autowired
    SingerService singerService;

    @GetMapping(value = "/")
    public String hello(){
        return " ✔ Up and Running";
    }

    @GetMapping(value = "/singers")
    public List<SingersDto> allSingers(){
        return singerService.saveTrader();
    }
}