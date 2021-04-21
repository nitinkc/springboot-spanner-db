package com.spanner.springBoot;

import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import model.spanner.Singers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TableService {
    @Autowired
    private SpannerTemplate spannerTemplate;
    public void saveTrader() {

        Singers t = new Singers();
        t.setTraderId(UUID.randomUUID());
        t.setFirstName("Nitin");
        t.setLastName("Chaurasia");
        t.setTemporaryNumber(22D);
        spannerTemplate.upsert(t);

    }
}
