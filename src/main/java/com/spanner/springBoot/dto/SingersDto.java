package com.spanner.springBoot.dto;

import com.google.spanner.v1.TypeCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;

@Getter
@Setter
@RequiredArgsConstructor
public class SingersDto {
    @Column(spannerType = TypeCode.STRING,name = "SINGER_ID")
    String SINGER_ID;
    @Column(spannerType = TypeCode.STRING,name = "FIRST_NAME")
    String FIRST_NAME;
    @Column(spannerType = TypeCode.STRING,name = "LAST_NAME")
    String LAST_NAME;
    @Column(spannerType = TypeCode.STRING,name = "SINGER_INFO")
    String singer_Info;
}
