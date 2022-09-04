package com.spanner.springBoot.dao.spanner;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SpannerWriter {

    private final SpannerTemplate spannerTemplate;

    public SpannerTemplate getSpannerTemplate() {
        return this.spannerTemplate;
    }

    public void upsertAll(Collection<?> entities) {
        //  fillAuditableFields(entities);
        spannerTemplate.upsertAll(entities);
    }
}