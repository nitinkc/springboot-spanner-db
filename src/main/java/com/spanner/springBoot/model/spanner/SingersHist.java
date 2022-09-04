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
@Table(name = SingersHist.NAME)
@NoArgsConstructor
public class SingersHist {

    public static final String NAME = "SINGERS_HIST";

    @Column(nullable = false, spannerTypeMaxLength = 36)
    @PrimaryKey(keyOrder = 1)
    private UUID singerHistId;

    public SingersHist(Singers singers) {
        setSingerHistId(UUID.randomUUID());
        try {
            BeanUtils.copyProperties(this, singers);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Singers data isn't correct", e);
        }
    }
}