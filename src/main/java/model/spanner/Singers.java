package model.spanner;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.NotMapped;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Table
@Data
public class Singers {

    @PrimaryKey
    @Column(name = "SingerId")
    Integer singerId;

    @Column(name = "FirstName")
    String firstName;

    @Column(name = "LastName")
    String lastName;

    @NotMapped
    Double temporaryNumber;
}
