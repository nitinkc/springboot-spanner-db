package com.spanner.springBoot.dao.spanner;

import com.google.cloud.spring.data.spanner.core.SpannerQueryOptions;
import com.google.cloud.spring.data.spanner.core.SpannerReadOptions;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import com.spanner.springBoot.Utilities.SqlFileUtil;
import com.spanner.springBoot.dao.AlbumReadDao;
import model.spanner.Albums;
import model.spanner.Singers;

import java.util.List;

public class AlbumRead extends SpannerReadDao<Albums> implements AlbumReadDao {
    public AlbumRead(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    private static final String FIND_ALBUMS_BY_SINGERS = SqlFileUtil.getSQLQuery("sql/find_albums_by_singers.sql");
    public static final SpannerQueryOptions SPANNER_OPTIONS = new SpannerQueryOptions();
    public static final SpannerReadOptions SPANNER_READ_OPTIONS = new SpannerReadOptions();

    static {
        SPANNER_OPTIONS.setAllowPartialRead(true);
        SPANNER_READ_OPTIONS.setAllowPartialRead(true);
    }

    @Override
    public List<Albums> getAlbumsBySinger(List<Singers> singers) {
        return null;
    }
}