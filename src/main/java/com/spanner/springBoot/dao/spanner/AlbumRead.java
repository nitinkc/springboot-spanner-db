package com.spanner.springBoot.dao.spanner;


import com.google.cloud.spanner.Statement;
import com.spanner.springBoot.Utilities.SqlFileUtil;
import com.spanner.springBoot.Utilities.TransactionalReadOnly;
import com.spanner.springBoot.dao.AlbumReadDao;
import com.spanner.springBoot.dto.AlbumsDto;
import com.spanner.springBoot.model.spanner.Albums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.data.spanner.core.SpannerQueryOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerReadOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
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
    @TransactionalReadOnly
    public List<AlbumsDto> getAlbumsBySingerIds(List<String> singerIds) {
        Statement.Builder builder = Statement
                .newBuilder(FIND_ALBUMS_BY_SINGERS)
                .bind("singerIds").toStringArray(singerIds);

        log.info(builder.build().getSql().toString());
        List<AlbumsDto> list = this.getSpannerTemplate().query(AlbumsDto.class,builder.build(), SPANNER_OPTIONS );
        log.info("Fetch a total of Albums : "  + list.size());

        return list;
    }
}