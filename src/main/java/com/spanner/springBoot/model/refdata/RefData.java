package com.spanner.springBoot.model.refdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

@Table(name = RefData.NAME)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RefData extends RefDisplay {

    public static final String NAME ="REF_DATA";
    private static final long serialVersionUID = -951835664226622910L;

    @PrimaryKey
    @Column(name = "CODE_ID", nullable = false)
    @JsonProperty("codeId")
    private String codeId;

    @Column(name = "NUMERIC_VALUE")
    private Integer numericValue;

}
