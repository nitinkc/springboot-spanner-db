package com.spanner.springBoot.model.refdata;

import com.google.spanner.v1.TypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReferenceDataBase implements Serializable {

    private static final long serialVersionUID = -830591559576473977L;

    @Column( name = "START_DATE",spannerType = TypeCode.TIMESTAMP)
    private Timestamp startDate;

    @Column(name = "END_DATE",spannerType = TypeCode.TIMESTAMP)
    private Timestamp endDate;

    @Column(name = "UPDATE_DATE_TIME_GMT",spannerType = TypeCode.TIMESTAMP)
    private Timestamp updateDateTimeGmt;

    @Column(name = "CREATE_RELEASE_NUMBER")
    private Integer releaseNumber;


}
