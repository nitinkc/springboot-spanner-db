package com.spanner.springBoot.service;

import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import com.spanner.springBoot.dao.spanner.SingerRead;
import model.spanner.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SingerService {
    @Autowired
    private SingerRead singerRead;

    public List<Singers> saveTrader() {
        return singerRead.findSingersByFirstChar('A');
    }
}
