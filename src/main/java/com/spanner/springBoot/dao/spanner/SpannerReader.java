package com.spanner.springBoot.dao.spanner;

import com.google.cloud.spanner.Struct;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.SpannerQueryOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerReadOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpannerReader {
    private SpannerTemplate spannerTemplate;
    public static final SpannerQueryOptions SPANNER_OPTIONS = new SpannerQueryOptions();
    public static final SpannerReadOptions SPANNER_READ_OPTIONS = new SpannerReadOptions();

    public SpannerTemplate getSpannerTemplate() {
        return this.spannerTemplate;
    }

    static {
        SPANNER_OPTIONS.setAllowPartialRead(true);
        SPANNER_READ_OPTIONS.setAllowPartialRead(true);
    }

    public String getString(String columnName, Struct struct) {
        if (isColumnMissing(columnName, struct) || struct.isNull(columnName)) {
            return null;
        } else {
            return struct.getString(columnName);
        }
    }

    public boolean isColumnMissing(String columnName, Struct struct) {
        try {
            struct.getColumnType(columnName);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
