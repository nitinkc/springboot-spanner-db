package com.spanner.springBoot.model.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class Audit extends DatesAudit implements Serializable {

    private Integer recordVersion;

    public void copyFieldsFromAudit(Audit audit) {
        setCreateDateTimeGmt(audit.getCreateDateTimeGmt());
        setUpdateDateTimeGmt(audit.getUpdateDateTimeGmt());
        setDeleteDateTimeGmt(audit.getDeleteDateTimeGmt());
        setRecordVersion(audit.getRecordVersion());
    }
}