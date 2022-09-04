package com.spanner.springBoot.model.refdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class RefDisplay extends ReferenceDataBase {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DISPLAY_NAME")
    private String displayName;
}
