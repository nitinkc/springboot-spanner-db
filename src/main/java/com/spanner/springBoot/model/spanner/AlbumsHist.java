package com.spanner.springBoot.model.spanner;

import com.google.spanner.v1.TypeCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Data
@SuperBuilder
@Table(name = AlbumsHist.NAME)
@NoArgsConstructor
public class AlbumsHist {

    public static final String NAME = "ALBUMS_HIST";

    @Column(nullable = false, spannerTypeMaxLength = 36)
    @PrimaryKey(keyOrder = 1)
    private UUID albumHistId;

    public AlbumsHist(Albums albums) {
        setAlbumHistId(UUID.randomUUID());
        try {
            BeanUtils.copyProperties(this, albums);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Albums data isn't correct", e);
        }
    }
}