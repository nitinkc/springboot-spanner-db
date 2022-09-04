package com.spanner.springBoot.dao.spanner;

import com.spanner.springBoot.dao.GenericReadDao;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class SpannerReadDao<T> implements GenericReadDao<T> {

    private SpannerTemplate spannerTemplate;

    public SpannerReadDao(SpannerTemplate spannerTemplate) {
        this.spannerTemplate = spannerTemplate;
    }

    protected SpannerTemplate getSpannerTemplate() {
        return spannerTemplate;
    }
}
