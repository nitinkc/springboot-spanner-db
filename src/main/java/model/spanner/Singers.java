package model.spanner;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.NotMapped;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Table(name = Singers.NAME )
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Singers {
    public static final String NAME = "Singers";

    @PrimaryKey
    @Column(name = "singerId")
    Integer singerId;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "singerInfo")
    String singerInfo;

    @NotMapped
    Double temporaryNumber;
}
