package com.spanner.springBoot.model.spanner;

import com.google.spanner.v1.TypeCode;
import lombok.*;
import com.spanner.springBoot.model.audit.Audit;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table(name = Albums.NAME )
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Albums extends Audit {
    public static final String NAME = "Albums";

    @PrimaryKey(keyOrder = 1)
    @Column(spannerType = TypeCode.STRING, name = "SINGER_ID")
    private UUID singerId;

    @PrimaryKey(keyOrder = 2)
    @Column(spannerType = TypeCode.STRING, name = "ALBUM_ID")
    private UUID albumId;

    @Column(spannerType = TypeCode.STRING, name = "ALBUM_TITLE")
    private String albumTitle;

    @Interleaved(lazy = true)
    private List<Singers> singers;
}
