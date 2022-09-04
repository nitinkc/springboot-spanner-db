package com.spanner.springBoot.service;

import com.spanner.springBoot.dao.spanner.SingerRead;
import com.spanner.springBoot.dto.SingersDto;
import com.spanner.springBoot.model.spanner.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerService {
    @Autowired
    private SingerRead singerRead;

    public List<SingersDto> saveTrader() {
        return singerRead.findSingersByFirstChar('Z');
    }
}
