package com.spanner.springBoot.model.spanner;

import com.google.spanner.v1.TypeCode;
import lombok.*;
import com.spanner.springBoot.model.audit.Audit;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

@Table(name = Singers.NAME )
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Getter
@Setter
public class Singers extends Audit {
    public static final String NAME = "Singers";

    @PrimaryKey(keyOrder = 1)
    @Column(spannerType = TypeCode.STRING,name = "SINGER_ID")
    String singerId;

    @Column(spannerType = TypeCode.STRING,name = "FIRST_NAME")
    String firstName;

    @Column(spannerType = TypeCode.STRING,name = "LAST_NAME")
    String lastName;

    @Column(spannerType = TypeCode.STRING,name = "SINGER_INFO")
    String singerInfo;
}
