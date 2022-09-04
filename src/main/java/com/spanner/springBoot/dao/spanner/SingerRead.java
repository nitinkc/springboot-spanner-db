package com.spanner.springBoot.dao.spanner;

import com.spanner.springBoot.Utilities.SqlFileUtil;
import com.spanner.springBoot.Utilities.TransactionalReadOnly;
import com.spanner.springBoot.dao.SingerReadDao;
import com.spanner.springBoot.dto.SingersDto;
import lombok.extern.slf4j.Slf4j;
import com.spanner.springBoot.model.spanner.Singers;

import com.google.cloud.spanner.Statement;
import org.springframework.cloud.gcp.data.spanner.core.SpannerQueryOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerReadOptions;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
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
    public List<SingersDto> findSingersByFirstChar(Character c){
        String firstChar = c + "%";

        Statement.Builder builder = Statement
                .newBuilder(SELECT_SINGERS_BY_FIRST_CHAR)
                .bind("firstChar")
                .to(firstChar);

        log.info(builder.build().getSql().toString());
        List<SingersDto> list = this.getSpannerTemplate().query(SingersDto.class,builder.build(), SPANNER_OPTIONS );
        log.info("Fetch a total of Singers : "  + list.size());

        return list;
    }
}
