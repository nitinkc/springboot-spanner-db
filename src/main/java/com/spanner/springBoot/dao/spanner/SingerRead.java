package com.spanner.springBoot.dao.spanner;

import com.google.cloud.spring.data.spanner.core.SpannerQueryOptions;
import com.google.cloud.spring.data.spanner.core.SpannerReadOptions;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import com.spanner.springBoot.Utilities.SqlFileUtil;
import com.spanner.springBoot.Utilities.TransactionalReadOnly;
import com.spanner.springBoot.dao.SingerReadDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.spanner.Singers;

import com.google.cloud.spanner.Statement;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Slf4j
public class SingerRead extends SpannerReadDao<Singers> implements SingerReadDao {
    public SingerRead(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    private static final String SELECT_SINGERS_BY_FIRST_CHAR = SqlFileUtil.getSQLQuery("sql/select_singers_by_first_char.sql");
    public static final SpannerQueryOptions SPANNER_OPTIONS = new SpannerQueryOptions();
    public static final SpannerReadOptions SPANNER_READ_OPTIONS = new SpannerReadOptions();

    static {
        SPANNER_OPTIONS.setAllowPartialRead(true);
        SPANNER_READ_OPTIONS.setAllowPartialRead(true);
    }

    @Override
    @TransactionalReadOnly
    public List<Singers> findSingersByFirstChar(Character c){
        String firstChar = c + "%";

        Statement.Builder build = Statement.newBuilder(SELECT_SINGERS_BY_FIRST_CHAR)
                .bind("firstChar").to("A%");

        log.info(build.build().getSql().toString());
        List<Singers> list = this.getSpannerTemplate()
                .query(struct -> getSpannerTemplate()
                                .getSpannerEntityProcessor()
                                .read(Singers.class, struct), build.build(), SPANNER_OPTIONS);

        return list;
    }
}
