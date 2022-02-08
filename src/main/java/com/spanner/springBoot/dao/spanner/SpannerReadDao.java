package com.spanner.springBoot.dao.spanner;

import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import com.spanner.springBoot.dao.GenericReadDao;
import org.springframework.stereotype.Component;

@Component
public abstract class SpannerReadDao<T> implements GenericReadDao<T> {

    private SpannerTemplate spannerTemplate;

    public SpannerReadDao(SpannerTemplate spannerTemplate) {
        this.spannerTemplate = spannerTemplate;
    }

    protected SpannerTemplate getSpannerTemplate() {
        return spannerTemplate;
    }
}
