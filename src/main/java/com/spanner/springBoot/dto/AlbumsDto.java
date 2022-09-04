package com.spanner.springBoot.dto;

import com.google.spanner.v1.TypeCode;
import com.spanner.springBoot.model.audit.Audit;
import com.spanner.springBoot.model.spanner.Singers;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AlbumsDto extends Audit {
    @Column(spannerType = TypeCode.STRING,name = "SINGER_ID")
    String SINGER_ID;
    @Column(spannerType = TypeCode.STRING,name = "ALBUM_ID")
    String ALBUM_ID;
    List<Singers> singers;
    @Column(spannerType = TypeCode.STRING, name = "ALBUM_TITLE")
    private String albumTitle;
}
