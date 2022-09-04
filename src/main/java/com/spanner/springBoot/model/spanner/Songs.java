package com.spanner.springBoot.model.spanner;

import com.google.spanner.v1.TypeCode;
import com.spanner.springBoot.model.audit.Audit;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table(name = Songs.NAME )
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Getter
@Setter
public class Songs extends Audit {
    public static final String NAME = "Somgs";

    @PrimaryKey(keyOrder = 1)
    @Column(spannerType = TypeCode.STRING, name = "SINGER_ID")
    private UUID singerId;

    @PrimaryKey(keyOrder = 2)
    @Column(spannerType = TypeCode.STRING, name = "ALBUM_ID")
    private UUID albumId;

    @PrimaryKey(keyOrder = 3)
    @Column(spannerType = TypeCode.STRING, name = "TRACK_ID")
    private UUID trackId;

    @Column(spannerType = TypeCode.STRING, name = "ALBUM_TITLE")
    private String albumTitle;

    @Interleaved(lazy = true)
    private List<Albums> albums;
}
