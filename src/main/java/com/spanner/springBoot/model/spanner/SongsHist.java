package com.spanner.springBoot.model.spanner;

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
@Table(name = SongsHist.NAME)
@NoArgsConstructor
public class SongsHist {

    public static final String NAME = "SONGS_HIST";

    @Column(nullable = false, spannerTypeMaxLength = 36)
    @PrimaryKey(keyOrder = 1)
    private UUID songsHistId;

    public SongsHist(Songs songs) {
        setSongsHistId(UUID.randomUUID());
        try {
            BeanUtils.copyProperties(this, songs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Songs data isn't correct", e);
        }
    }
}