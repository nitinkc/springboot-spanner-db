package com.spanner.springBoot.model.audit;

import com.google.spanner.v1.TypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DatesAudit implements Serializable {

    @Column(spannerType = TypeCode.TIMESTAMP, nullable = false)
    private Timestamp createDateTimeGmt;

    @Column(spannerType = TypeCode.TIMESTAMP)
    private Timestamp updateDateTimeGmt;

    @Column(spannerType = TypeCode.TIMESTAMP)
    private Timestamp deleteDateTimeGmt;

}
